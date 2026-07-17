package io.github.ennuil.crooked_crooks;

import io.github.ennuil.crooked_crooks.init.CrookedItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;

public class ModInit implements ModInitializer { // TODO move
	@Override
	public void onInitialize() {
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
			entries.insertAfter(Items.WOODEN_HOE, CrookedItems.WOODEN_CROOK.get());
			entries.insertAfter(Items.STONE_HOE, CrookedItems.STONE_CROOK.get());
			entries.insertAfter(Items.IRON_HOE, CrookedItems.IRON_CROOK.get());
			entries.insertBefore(Items.BUCKET, CrookedItems.BONE_CROOK.get());
		});
	}
}
