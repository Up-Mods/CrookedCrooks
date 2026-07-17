package io.github.ennuil.crooked_crooks.item;

import io.github.ennuil.crooked_crooks.data.CrookedTags;
import io.github.ennuil.crooked_crooks.init.CrookedMobEffects;
import io.github.ennuil.crooked_crooks.satire_config.SatireConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.stats.Stats;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.component.Weapon;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CrookItem extends Item {
	// TODO namespace!
	public static final Identifier BASE_BLOCK_REACH = Identifier.withDefaultNamespace("base_block_reach");
	public static final Identifier BASE_ENTITY_REACH = Identifier.withDefaultNamespace("base_entity_reach");

	private final float crookStrength;

	public CrookItem(ToolMaterial material, float attackDamage, float attackSpeed, float crookStrength, Properties properties) {
		super(applyProperties(properties, CrookedTags.Blocks.MINEABLE_WITH_CROOK, attackDamage, attackSpeed, material));
		this.crookStrength = crookStrength;
	}

	// TODO - Mimic the ongoing migration to separate methods for setting the properties
	public static Item.Properties applyProperties(Item.Properties properties, TagKey<Block> mineableBlocks, float attackDamage, float attackSpeed, ToolMaterial material) {
		var holderGetter = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);
		return properties.durability(material.durability())
			.repairable(material.repairItems())
			.enchantable(material.enchantmentValue())
			.component(DataComponents.TOOL,
				new Tool(List.of(
					Tool.Rule.deniesDrops(holderGetter.getOrThrow(material.incorrectBlocksForDrops())),
					Tool.Rule.minesAndDrops(holderGetter.getOrThrow(mineableBlocks), material.speed())
				), 1.0F, 1, true))
			.attributes(createAttributes(attackDamage + material.attackDamageBonus(), attackSpeed))
			.component(DataComponents.WEAPON, new Weapon(2, 0.0F));
	}

	public static ItemAttributeModifiers createAttributes(float attackDamage, float attackSpeed) {
		return ItemAttributeModifiers.builder()
			.add(Attributes.ATTACK_DAMAGE, new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, attackDamage, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
			.add(Attributes.ATTACK_SPEED, new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, attackSpeed, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
			.add(Attributes.BLOCK_INTERACTION_RANGE, new AttributeModifier(BASE_BLOCK_REACH, 3.0F, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
			.add(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(BASE_ENTITY_REACH, 3.0F, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
			.build();
	}

	@Override
	public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		stack.hurtAndBreak(2, attacker, EquipmentSlot.MAINHAND);
	}

	@Override
	public @NotNull InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity interactionTarget, InteractionHand usedHand) {
		if (!player.getCooldowns().isOnCooldown(stack)) {
			float mobWeight = interactionTarget.getBbWidth() * interactionTarget.getBbHeight();
			float actualWeight = 1.0F / (Math.max(mobWeight, 0.2F) / this.crookStrength);

			if (SatireConfig.getConfig().shepherdsTouch() && interactionTarget instanceof Sheep) {
				// 7 seconds * 20 ticks = 140 ticks
				player.addEffect(new MobEffectInstance(CrookedMobEffects.SHEPHERDS_TOUCH.holder(), 7 * 20), interactionTarget);
			}

			var pos = interactionTarget.position().vectorTo(player.position()).normalize();
			if (player.isShiftKeyDown()) {
				pos = pos.multiply(0.5F, 1.25F, 0.5F);
			} else {
				pos = pos.scale(0.75F);
			}
			pos = pos.scale(actualWeight);

			interactionTarget.push(pos);

			stack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
			player.awardStat(Stats.ITEM_USED.get(this));
			player.getCooldowns().addCooldown(stack, 6);

			return InteractionResult.SUCCESS;
		}

		return super.interactLivingEntity(stack, player, interactionTarget, usedHand);
	}

	// Make instabreak blocks damage this thing!
	@Override
	public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity) {
		var tool = stack.get(DataComponents.TOOL);
		if (tool == null) {
			return false;
		} else {
			if (!level.isClientSide() && (state.getDestroySpeed(level, pos) != 0.0F || state.is(CrookedTags.Blocks.MINEABLE_WITH_CROOK)) && tool.damagePerBlock() > 0) {
				stack.hurtAndBreak(tool.damagePerBlock(), miningEntity, EquipmentSlot.MAINHAND);
			}

			return true;
		}
	}
}
