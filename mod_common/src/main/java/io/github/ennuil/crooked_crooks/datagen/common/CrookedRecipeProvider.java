package io.github.ennuil.crooked_crooks.datagen.common;

import dev.upcraft.sparkweave.api.datagen.provider.common.SparkweaveRecipeProvider;
import io.github.ennuil.crooked_crooks.data.CrookedTags;
import io.github.ennuil.crooked_crooks.init.CrookedItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

public class CrookedRecipeProvider extends SparkweaveRecipeProvider {

	public CrookedRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
		super(registries, output);
	}

	@Override
	public void buildRecipes() {
		crook(CrookedItems.BONE_CROOK.get(), CrookedTags.Items.BONE_TOOL_MATERIALS);
		crook(CrookedItems.WOODEN_CROOK.get(), commonTag("rods/wooden"));
		crook(CrookedItems.STONE_CROOK.get(), ItemTags.STONE_TOOL_MATERIALS);
		crook(CrookedItems.IRON_CROOK.get(), ItemTags.IRON_TOOL_MATERIALS);
	}

	// TODO make constants for conventional tags
	private static TagKey<Item> commonTag(String name) {
		return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", name));
	}

	private void crook(ItemLike crook, ItemLike material) {
		this.shaped(RecipeCategory.TOOLS, crook)
			.pattern("##")
			.pattern(" #")
			.pattern(" #")
			.define('#', material)
			.unlockedBy(getHasName(material), has(material))
			.save(output);
	}

	private void crook(ItemLike crook, TagKey<Item> material) {
		this.shaped(RecipeCategory.TOOLS, crook)
			.pattern("##")
			.pattern(" #")
			.pattern(" #")
			.define('#', material)
			.unlockedBy("has_" + material.location().getPath(), this.has(material))
			.save(output);
	}
}
