package io.github.ennuil.crooked_crooks.emi;

import dev.emi.emi.api.recipe.EmiIngredientRecipe;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.recipe.EmiResolutionRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import io.github.ennuil.crooked_crooks.CrookedCrooksMod;
import io.github.ennuil.crooked_crooks.items.CrookTags;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CrookDropEfficiencyEmiRecipe extends EmiIngredientRecipe {
	private final EmiIngredient tagIngredient = EmiIngredient.of(CrookTags.CROOKS, 1);
	private final List<EmiStack> stacks;
	private final int multiplier;

	public CrookDropEfficiencyEmiRecipe(int multiplier, List<EmiStack> inputs) {
		this.stacks = inputs;
		this.multiplier = multiplier;
	}

	@Override
	public EmiRecipeCategory getCategory() {
		return CrookedCrooksEmiPlugin.CROOK_RECIPE_CATEGORY;
	}

	@Override
	public @Nullable Identifier getId() {
		return new Identifier(CrookedCrooksMod.MODID, "emi/crook_drop_efficiency/" + multiplier);
	}

	@Override
	protected EmiIngredient getIngredient() {
		return tagIngredient;
	}

	@Override
	protected List<EmiStack> getStacks() {
		return stacks;
	}

	@Override
	protected EmiRecipe getRecipeContext(EmiStack stack, int offset) {
		return new EmiResolutionRecipe(tagIngredient, stack);
	}

	@Override
	public void addWidgets(WidgetHolder widgets) {
		super.addWidgets(widgets);
		widgets.addText(Text.translatable("emi.recipe.crooked_crooks.crook_efficiency.drop_multiplier", multiplier), (144 + 18) / 2 + 3, 5, 0xFFFFFF, true);
	}
}
