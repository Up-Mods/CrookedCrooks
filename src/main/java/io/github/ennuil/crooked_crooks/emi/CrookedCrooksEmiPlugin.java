package io.github.ennuil.crooked_crooks.emi;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import io.github.ennuil.crooked_crooks.CrookedCrooksMod;
import io.github.ennuil.crooked_crooks.items.CrookTags;
import it.unimi.dsi.fastutil.ints.Int2ReferenceAVLTreeMap;
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
		registry.addWorkstation(CROOK_RECIPE_CATEGORY, EmiIngredient.of(CrookTags.CROOKS));

		SortedMap<Integer, List<EmiStack>> reverseMap = new Int2ReferenceAVLTreeMap<>();

		for (var entry : CrookedCrooksMod.CROOK_EFFECTIVE) {
			var stack = EmiStack.of(entry.entry());
			if (!stack.isEmpty()) {
				reverseMap.computeIfAbsent(entry.value(), k -> new ArrayList<>()).add(stack);
			}
		}

		var tag = EmiIngredient.of(CrookTags.CROOKS, 1);
		reverseMap.forEach((multiplier, items) -> registry.addRecipe(new CrookDropEfficiencyEmiRecipe(tag, multiplier, items)));
	}
}
