package io.github.ennuil.crooked_crooks;

import com.google.auto.service.AutoService;
import dev.upcraft.sparkweave.api.entrypoint.MainEntryPoint;
import dev.upcraft.sparkweave.api.platform.ModContainer;
import io.github.ennuil.crooked_crooks.crookdata.CrookData;
import io.github.ennuil.crooked_crooks.data.CrookedTags;
import io.github.ennuil.crooked_crooks.init.CrookedItems;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

@AutoService(MainEntryPoint.class)
public class CrookedCrooksFabric implements MainEntryPoint {
	@Override
	public void onInitialize(ModContainer mod) {
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
			entries.insertAfter(Items.WOODEN_HOE, CrookedItems.WOODEN_CROOK.get());
			entries.insertAfter(Items.STONE_HOE, CrookedItems.STONE_CROOK.get());
			entries.insertAfter(Items.IRON_HOE, CrookedItems.IRON_CROOK.get());
			entries.insertBefore(Items.BUCKET, CrookedItems.BONE_CROOK.get());
		});

		DynamicRegistries.register(CrookData.REGISTRY_KEY, CrookData.CODEC);

		LootTableEvents.MODIFY_DROPS.register((holder, context, drops) -> {
			var blockState = context.getOptionalParameter(LootContextParams.BLOCK_STATE);
			if(blockState == null) {
				return;
			}

			var tool = context.getOptionalParameter(LootContextParams.TOOL);
			if(tool == null || !tool.is(CrookedTags.Items.CROOKS) || context.getResolver().lookupOrThrow(Registries.ENCHANTMENT).get(Enchantments.SILK_TOUCH).map(ench -> EnchantmentHelper.getItemEnchantmentLevel(ench, tool) > 0).orElse(false)) {
				return;
			}

			CrookData.getFor(context.getLevel().registryAccess(), blockState).ifPresent(data -> {
				for (int i = 0; i < data.bonusRolls(); i++) {
					holder.value().getRandomItems(context, drops::add);
				}
			});

			// TODO data-map-like thing to make this value dynamic per block
		});
	}
}
