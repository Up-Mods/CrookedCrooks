package io.github.ennuil.crooked_crooks.items;


import io.github.ennuil.crooked_crooks.CrookedCrooksMod;
import net.fabricmc.fabric.api.mininglevel.v1.MiningLevelManager;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.quiltmc.loader.api.QuiltLoader;

public class CrookItem extends MiningToolItem {
	public float crookStrength;

	public CrookItem(ToolMaterial material, float attackDamage, float attackSpeed, float crookStrength, Item.Settings settings) {
		super(attackDamage, attackSpeed, material, null, settings);
		this.crookStrength = crookStrength;
	}

	@Override
	public boolean isSuitableFor(BlockState state) {
		int miningLevel = this.getMaterial().getMiningLevel();
		if (miningLevel >= MiningLevelManager.getRequiredMiningLevel(state)) {
			return CrookedCrooksMod.CROOK_EFFECTIVE.get(state.getBlock()).isPresent();
		}

		return false;
	}

	@Override
	public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
		return this.isSuitableFor(state) ? this.miningSpeed : 1.0F;
	}

	@Override
	public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
		if (!world.isClient && (state.getHardness(world, pos) != 0.0F || this.isSuitableFor(state))) {
			stack.damage(1, miner, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
		}

		return true;
	}

	//Handles the pulling of mobs with a crook
	@Override
	public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
		//Restrict the pulling through a cooldown.
		if (!user.getItemCooldownManager().isCoolingDown(this)) {
			// This calculates the weight of the pull. If it's 1.0D, it means that it's a full power pull
			float crookStrength = this.crookStrength;
			double mobWeight = CrookedCrooksMod.ENTITY_WEIGHT.get(entity.getType()).orElseGet(() -> entity.getBoundingBox().getAverageSideLength());
			double weight = Math.min(crookStrength / mobWeight, 1.0D);
			if (QuiltLoader.isDevelopmentEnvironment()) {
				user.sendMessage(Text.literal("" + crookStrength / mobWeight + " (" + mobWeight + " ₢" + crookStrength + ")"), true);
			}

			// Calculates the vector used to pull the mob
			Vec3d pos = user.getPos().subtract(entity.getPos());
			pos = pos.subtract(user.getRotationVector());
			// This makes sure that the mob isn't simply flinged
			pos = pos.multiply(0.275);
			pos = pos.multiply(weight);

			// With everything in order, pull the mob! (and set fall distance to 0 so crooking isn't lethal)
			entity.setVelocity(pos);
			entity.velocityModified = true;
			entity.fallDistance = 0.0F;

			// If cursed with the Curse of Thorns, deal damage to the mob
			if (EnchantmentHelper.getLevel(CrookedCrooksMod.THORNS_CURSE_ENCHANTMENT, stack) > 0) {
				entity.setAttacker(user);
				entity.setAttacking(user);
				entity.damage(entity.getDamageSources().generic(), this.getAttackDamage() * user.getAttackCooldownProgress(0.5F));
				user.resetLastAttackedTicks();
			}

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
