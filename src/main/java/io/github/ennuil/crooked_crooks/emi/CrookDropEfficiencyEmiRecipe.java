package io.github.ennuil.crooked_crooks.emi;

import dev.emi.emi.api.recipe.EmiIngredientRecipe;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.recipe.EmiResolutionRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import io.github.ennuil.crooked_crooks.CrookedCrooksMod;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CrookDropEfficiencyEmiRecipe extends EmiIngredientRecipe {
	private final EmiIngredient displayIngredient;
	private final int multiplier;
	private final List<EmiStack> stacks;

	public CrookDropEfficiencyEmiRecipe(EmiIngredient displayIngredient, int multiplier, List<EmiStack> stacks) {
        this.displayIngredient = displayIngredient;
		this.multiplier = multiplier;
		this.stacks = stacks;
	}

	@Override
	public EmiRecipeCategory getCategory() {
		return CrookedCrooksEmiPlugin.CROOK_RECIPE_CATEGORY;
	}

	@Override
	public @Nullable Identifier getId() {
		return new Identifier(CrookedCrooksMod.MOD_ID, "emi/crook_drop_efficiency/" + multiplier);
	}

	@Override
	protected EmiIngredient getIngredient() {
		return displayIngredient;
	}

	@Override
	protected List<EmiStack> getStacks() {
		return stacks;
	}

	@Override
	protected EmiRecipe getRecipeContext(EmiStack stack, int offset) {
		return new EmiResolutionRecipe(displayIngredient, stack);
	}

	@Override
	public void addWidgets(WidgetHolder widgets) {
		super.addWidgets(widgets);
		widgets.addText(Text.translatable("emi.recipe.crooked_crooks.crook_efficiency.drop_multiplier", multiplier), (144 + 18) / 2 + 3, 5, 0xFFFFFF, true);
	}
}
