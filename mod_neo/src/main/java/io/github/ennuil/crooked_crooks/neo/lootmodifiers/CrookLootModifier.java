package io.github.ennuil.crooked_crooks.neo.lootmodifiers;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.ennuil.crooked_crooks.crookdata.CrookData;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

public class CrookLootModifier extends LootModifier {

	public static final MapCodec<CrookLootModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
		LootModifier.codecStart(instance).apply(instance, CrookLootModifier::new)
	);

	public CrookLootModifier(LootItemCondition[] conditions, int priority) {
		super(conditions, priority);
	}

	@Override
	protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		var blockState = context.getOptionalParameter(LootContextParams.BLOCK_STATE);
		if(blockState == null) {
			return generatedLoot;
		}

		var tool = context.getOptionalParameter(LootContextParams.TOOL);
		if(tool != null && context.getResolver().lookupOrThrow(Registries.ENCHANTMENT).get(Enchantments.SILK_TOUCH).map(ench -> tool.getEnchantmentLevel(ench) > 0).orElse(false)) {
			return generatedLoot;
		}

		CrookData.getFor(context.getLevel().registryAccess(), blockState).ifPresent(data -> {
			for (int i = 0; i < data.bonusRolls(); i++) {
				context.addDynamicDrops(context.getQueriedLootTableId(), generatedLoot::add);
			}
		});

		return generatedLoot;
	}

	@Override
	public MapCodec<? extends IGlobalLootModifier> codec() {
		return CODEC;
	}
}
