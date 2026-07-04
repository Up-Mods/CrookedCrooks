package io.github.ennuil.crooked_crooks.events;

import io.github.ennuil.crooked_crooks.block.data_maps.ModBlockDataMaps;
import io.github.ennuil.crooked_crooks.entity.ModAttributes;
import io.github.ennuil.crooked_crooks.item.ModItems;
import io.github.ennuil.crooked_crooks.utils.ModUtils;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = ModUtils.MOD_NAMESPACE)
public class ModEvents {
	@SubscribeEvent
	public static void registerDataMaps(RegisterDataMapTypesEvent event) {
		event.register(ModBlockDataMaps.MULTIPLIES_DROPS);
	}

	@SubscribeEvent
	public static void addSheepAttribute(EntityAttributeModificationEvent event) {
		event.add(EntityType.SHEEP, ModAttributes.HERDING_RANGE, 20.0);
	}

	@SubscribeEvent
	public static void addItemsToCreativeTab(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			event.insertAfter(
				Items.WOODEN_HOE.getDefaultInstance(),
				ModItems.WOODEN_CROOK.get().getDefaultInstance(),
				CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
			);

			event.insertAfter(
				Items.STONE_HOE.getDefaultInstance(),
				ModItems.STONE_CROOK.get().getDefaultInstance(),
				CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
			);

			event.insertAfter(
				Items.IRON_HOE.getDefaultInstance(),
				ModItems.IRON_CROOK.get().getDefaultInstance(),
				CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
			);

			event.insertBefore(
				Items.BUCKET.getDefaultInstance(),
				ModItems.BONE_CROOK.get().getDefaultInstance(),
				CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
			);
		}
	}
}
