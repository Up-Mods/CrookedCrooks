package io.github.ennuil.crooked_crooks.datagen.common;

import dev.upcraft.sparkweave.api.datagen.provider.common.SparkweaveBlockTagProvider;
import dev.upcraft.sparkweave.api.datagen.provider.common.SparkweaveItemTagProvider;
import io.github.ennuil.crooked_crooks.CrookedCrooks;
import io.github.ennuil.crooked_crooks.data.CrookedTags;
import io.github.ennuil.crooked_crooks.init.CrookedItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class CrookedItemTagProvider extends SparkweaveItemTagProvider {

	public CrookedItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, SparkweaveBlockTagProvider blockTagProvider) {
		super(output, CrookedCrooks.MOD_NAMESPACE, lookupProvider, blockTagProvider);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		tag(CrookedTags.Items.CROOKS, "Crooks")
			.add(CrookedItems.WOODEN_CROOK)
			.add(CrookedItems.BONE_CROOK)
			.add(CrookedItems.STONE_CROOK)
			.add(CrookedItems.IRON_CROOK);

		existingTag(CrookedTags.Items.BONE_TOOL_MATERIALS)
			.add(Items.BONE);

		existingTag(ItemTags.DURABILITY_ENCHANTABLE)
			.addTag(CrookedTags.Items.CROOKS);

		existingTag(ItemTags.MINING_ENCHANTABLE)
			.addTag(CrookedTags.Items.CROOKS);

		existingTag(ItemTags.MINING_LOOT_ENCHANTABLE)
			.addTag(CrookedTags.Items.CROOKS);
	}
}
