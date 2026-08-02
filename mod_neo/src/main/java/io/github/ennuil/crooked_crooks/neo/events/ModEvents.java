package io.github.ennuil.crooked_crooks.neo.events;

import io.github.ennuil.crooked_crooks.CrookedCrooks;
import io.github.ennuil.crooked_crooks.init.CrookedAttributes;
import io.github.ennuil.crooked_crooks.init.CrookedItems;
import io.github.ennuil.crooked_crooks.neo.data_maps.CrookedDataMaps;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

@EventBusSubscriber(modid = CrookedCrooks.MOD_NAMESPACE)
public class ModEvents {
	@SubscribeEvent
	public static void registerDataMaps(RegisterDataMapTypesEvent event) {
		event.register(CrookedDataMaps.MULTIPLIES_DROPS);
	}

	@SubscribeEvent
	public static void addSheepAttribute(EntityAttributeModificationEvent event) {
		event.add(EntityType.SHEEP, CrookedAttributes.HERDING_RANGE.holder(), 20.0);
	}

	@SubscribeEvent
	public static void addItemsToCreativeTab(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			event.insertAfter(
				Items.WOODEN_HOE.getDefaultInstance(),
				CrookedItems.WOODEN_CROOK.get().getDefaultInstance(),
				CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
			);

			event.insertAfter(
				Items.STONE_HOE.getDefaultInstance(),
				CrookedItems.STONE_CROOK.get().getDefaultInstance(),
				CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
			);

			event.insertAfter(
				Items.IRON_HOE.getDefaultInstance(),
				CrookedItems.IRON_CROOK.get().getDefaultInstance(),
				CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
			);

			event.insertBefore(
				Items.BUCKET.getDefaultInstance(),
				CrookedItems.BONE_CROOK.get().getDefaultInstance(),
				CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
			);
		}
	}
}
