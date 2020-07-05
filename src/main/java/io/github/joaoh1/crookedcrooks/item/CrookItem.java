package io.github.joaoh1.crookedcrooks.item;

import io.github.joaoh1.crookedcrooks.CrookedCrooksMod;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CrookItem extends MiningToolItem {
	public float pullingPower;

	public CrookItem(ToolMaterial material, float attackDamage, float attackSpeed, float pullingPower, Item.Settings settings) {
		super(attackDamage, attackSpeed, material, null, settings);
		this.pullingPower = pullingPower;
	}

	public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
		if (!state.isIn(CrookedCrooksMod.CROOK_EFFECTIVE)) {
		   return 1.0F;
		} else {
		   return this.miningSpeed;
		}
	}

	@Override
	public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
		if (!world.isClient && state.isIn(CrookedCrooksMod.CROOK_EFFECTIVE)) {
			stack.damage(1, miner, e -> {
				e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
			});
			return true;
		}
		return super.postMine(stack, world, state, pos, miner);
	}

	//Handles the pulling of mobs with a crook.
	@Override
	public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
		//Restrict the pulling through a cooldown.
		if (!user.getItemCooldownManager().isCoolingDown(this)) {
			int toolDamage = 1;
			double appliedPullingPower = pullingPower;
			//Make size and distance to the mob affect the pull.
			appliedPullingPower /= entity.getBoundingBox().getAverageSideLength();
			appliedPullingPower /= entity.distanceTo(user);
			//If sneaking, raise the mob vertically in cost of additional damage.
			if (user.isSneaking()) {
				entity.addVelocity(0.0, appliedPullingPower, 0.0);
				toolDamage++;
			}
			//Finally, pull the mob.
			entity.takeKnockback(pullingPower, entity.getX() - user.getX(), entity.getZ() - user.getZ());
			//Damage the crook.
			stack.damage(toolDamage, user, e -> {
				e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
			});
			//Increment the statistics.
			user.incrementStat(Stats.USED.getOrCreateStat(this));
			//Sets a cooldown on the crook for balance purposes.
			user.getItemCooldownManager().set(this, 5);
			return ActionResult.SUCCESS;
		}
		return ActionResult.PASS;
	}
}