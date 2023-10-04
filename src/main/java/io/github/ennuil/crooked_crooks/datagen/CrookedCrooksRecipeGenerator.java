package io.github.ennuil.crooked_crooks.datagen;

import io.github.ennuil.crooked_crooks.CrookedCrooksMod;
import io.github.ennuil.crooked_crooks.items.CrookTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory;
import net.minecraft.data.server.recipe.TransformSmithingRecipeJsonFactory;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class CrookedCrooksRecipeGenerator extends FabricRecipeProvider {

	public CrookedCrooksRecipeGenerator(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
		ShapedRecipeJsonFactory.create(RecipeCategory.TOOLS, CrookedCrooksMod.WOODEN_CROOK_ITEM).ingredient('#', CrookTags.Common.WOODEN_STICKS).pattern("##").pattern(" #").pattern(" #").criterion("has_stick", conditionsFromItemTag(CrookTags.Common.WOODEN_STICKS)).offerTo(exporter);

		//TODO perhaps create "stone rods" and use these?
		ShapedRecipeJsonFactory.create(RecipeCategory.TOOLS, CrookedCrooksMod.STONE_CROOK_ITEM).ingredient('#', ItemTags.STONE_TOOL_MATERIALS).pattern("##").pattern(" #").pattern(" #").criterion("has_stone", conditionsFromItemTag(ItemTags.STONE_TOOL_MATERIALS)).offerTo(exporter);
		ShapedRecipeJsonFactory.create(RecipeCategory.TOOLS, CrookedCrooksMod.IRON_CROOK_ITEM).ingredient('#', CrookTags.Common.IRON_NUGGETS).pattern("##").pattern(" #").pattern(" #").criterion("has_iron", conditionsFromItemTag(CrookTags.Common.IRON_NUGGETS)).offerTo(exporter);
		ShapedRecipeJsonFactory.create(RecipeCategory.TOOLS, CrookedCrooksMod.GOLDEN_CROOK_ITEM).ingredient('#', CrookTags.Common.GOLD_NUGGETS).pattern("##").pattern(" #").pattern(" #").criterion("has_gold", conditionsFromItemTag(CrookTags.Common.GOLD_NUGGETS)).offerTo(exporter);

		TransformSmithingRecipeJsonFactory.create(Ingredient.empty(), Ingredient.ofItems(CrookedCrooksMod.IRON_CROOK_ITEM), Ingredient.ofTag(CrookTags.Common.DIAMONDS), RecipeCategory.TOOLS, CrookedCrooksMod.DIAMOND_CROOK_ITEM).criterion("has_diamond", conditionsFromItemTag(CrookTags.Common.DIAMONDS)).offerTo(exporter, new Identifier(CrookedCrooksMod.MODID, getItemPath(CrookedCrooksMod.DIAMOND_CROOK_ITEM) + "_smithing"));
		offerNetheriteUpgradeRecipe(exporter, CrookedCrooksMod.DIAMOND_CROOK_ITEM, RecipeCategory.TOOLS, CrookedCrooksMod.NETHERITE_CROOK_ITEM);

//		TransformSmithingRecipeJsonFactory.create(Ingredient.empty(), Ingredient.ofItems(CrookedCrooksMod.IRON_CROOK_ITEM), Ingredient.ofTag(CrookTags.Common.COPPER_INGOTS), RecipeCategory.TOOLS, CrookedCrooksMod.DIAMOND_CROOK_ITEM).criterion("has_copper", conditionsFromItemTag(CrookTags.Common.COPPER_INGOTS)).offerTo(exporter, new Identifier(CrookedCrooksMod.MODID, getItemPath(CrookedCrooksMod.COPPER_CR) + "_smithing"));

//		TransformSmithingRecipeJsonFactory.create(Ingredient.empty(), Ingredient.ofItems(CrookedCrooksMod.IRON_CROOK_ITEM), Ingredient.ofTag(ConventionalItemTags.DIAMONDS), RecipeCategory.TOOLS, CrookedCrooksMod.DIAMOND_CROOK_ITEM).criterion("has_diamond", conditionsFromItemTag(ConventionalItemTags.DIAMONDS)).offerTo(exporter, new Identifier(CrookedCrooksMod.MODID, getItemPath(CrookedCrooksMod.DIAMOND_CROOK_ITEM) + "_smithing"));
	}
}
