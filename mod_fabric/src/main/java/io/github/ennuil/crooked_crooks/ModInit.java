package io.github.ennuil.crooked_crooks;

import com.google.auto.service.AutoService;
import dev.upcraft.sparkweave.api.entrypoint.MainEntryPoint;
import dev.upcraft.sparkweave.api.platform.ModContainer;
import io.github.ennuil.crooked_crooks.init.CrookedItems;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;

@AutoService(MainEntryPoint.class)
public class ModInit implements MainEntryPoint { // TODO move
	@Override
	public void onInitialize(ModContainer mod) {
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
			entries.insertAfter(Items.WOODEN_HOE, CrookedItems.WOODEN_CROOK.get());
			entries.insertAfter(Items.STONE_HOE, CrookedItems.STONE_CROOK.get());
			entries.insertAfter(Items.IRON_HOE, CrookedItems.IRON_CROOK.get());
			entries.insertBefore(Items.BUCKET, CrookedItems.BONE_CROOK.get());
		});
	}
}
