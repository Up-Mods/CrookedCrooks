package io.github.ennuil.crooked_crooks.emi;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import io.github.ennuil.crooked_crooks.CrookedCrooksMod;
import it.unimi.dsi.fastutil.ints.Int2ReferenceAVLTreeMap;
import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

public class CrookedCrooksEmiPlugin implements EmiPlugin {
	private static final EmiStack CROOK_ICON = EmiStack.of(CrookedCrooksMod.WOODEN_CROOK_ITEM);
	public static final EmiRecipeCategory CROOK_RECIPE_CATEGORY = new EmiRecipeCategory(
		new Identifier(CrookedCrooksMod.MODID, "emi_recipe_category"), CROOK_ICON
	);

	@Override
	public void register(EmiRegistry registry) {
		registry.addCategory(CROOK_RECIPE_CATEGORY);
		registry.addWorkstation(CROOK_RECIPE_CATEGORY, EmiIngredient.of(CrookedCrooksMod.CROOKS));

		SortedMap<Integer, List<Block>> reverseMap = new Int2ReferenceAVLTreeMap<>();

		for (var entry : CrookedCrooksMod.CROOK_EFFECTIVE) {
			if (entry.entry().asItem() != Items.AIR) {
				if (reverseMap.containsKey(entry.value())) {
					reverseMap.get(entry.value()).add(entry.entry());
				} else {
					List<Block> list = new ArrayList<>();
					list.add(entry.entry());
					reverseMap.put(entry.value(), list);
				}
			}
		}

		reverseMap.forEach((multiplier, blocks) -> {
			registry.addRecipe(new CrookDropEfficiencyEmiRecipe(multiplier, blocks));
		});
	}
}
