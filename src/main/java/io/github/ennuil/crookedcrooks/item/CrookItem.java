package io.github.ennuil.crookedcrooks.item;

import io.github.ennuil.crookedcrooks.CrookedCrooksMod;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.stat.Stats;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class CrookItem extends MiningToolItem {
	public float pullingPower;

	public CrookItem(ToolMaterial material, float attackDamage, float attackSpeed, float pullingPower, Item.Settings settings) {
		super(attackDamage, attackSpeed, material, null, settings);
		// TODO - Move the pulling power registry to a data-driven system
		this.pullingPower = pullingPower;
	}

	// TODO - Actually update this to 1.17 
	@Override
	public boolean isSuitableFor(BlockState state) {
		return CrookedCrooksMod.CROOK_EFFECTIVE.contains(state.getBlock());
	}

	@Override
	public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
		if (!this.isSuitableFor(state)) {
		   return 1.0F;
		} else {
		   return this.miningSpeed;
		}
	}

	@Override
	public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
		if (!world.isClient && CrookedCrooksMod.CROOK_EFFECTIVE.contains(state.getBlock())) {
			stack.damage(1, miner, e -> {
				e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
			});
			return true;
		}
		return super.postMine(stack, world, state, pos, miner);
	}

	//Handles the pulling of mobs with a crook
	@Override
	public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
		//Restrict the pulling through a cooldown.
		if (!user.getItemCooldownManager().isCoolingDown(this)) {
			// This calculates the weight of the pull. If it's 1.0D, it means that it's a full power pull
			float crookStrength = this.pullingPower;
			double mobWeight = entity.getBoundingBox().getAverageSideLength();
			double weight = (crookStrength / mobWeight) <= 1.0D ? crookStrength / mobWeight : 1.0D;
			user.sendMessage(new LiteralText("" + crookStrength / mobWeight + " (" + mobWeight + " ₢" + crookStrength + ")"), true);

			// Calculates the vector used to pull the mob
			Vec3d pos = user.getPos().subtract(entity.getPos());
			pos = pos.subtract(user.getRotationVector());
			// This makes sure that the mob isn't simply flinged
			pos = pos.multiply(0.275);
			pos = pos.multiply(weight);

			// With everything in order, pull the mob! (and set fall distance to 0 so crooking isn't lethal)
			entity.setVelocity(pos);
			entity.fallDistance = 0.0F;

			//Damage the crook.
			stack.damage(1, user, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));

			//Increment the statistics
			user.incrementStat(Stats.USED.getOrCreateStat(this));

			//Sets a cooldown on the crook for balance purposes
			user.getItemCooldownManager().set(this, 6);

			return ActionResult.SUCCESS;
		}
		return ActionResult.PASS;
	}
}