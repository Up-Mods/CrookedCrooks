package io.github.ennuil.crooked_crooks.emi;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import io.github.ennuil.crooked_crooks.CrookedCrooksMod;
import net.minecraft.block.Block;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class CrookEmiRecipe implements EmiRecipe {
	private final List<EmiStack> stackList = new ArrayList<>();
	private final int multiplier;

	public CrookEmiRecipe(int multiplier, List<Block> blockList) {
		blockList.stream().forEach(block -> this.stackList.add(EmiStack.of(block)));
		this.multiplier = multiplier;
	}

	@Override
	public EmiRecipeCategory getCategory() {
		return CrookedCrooksEmiPlugin.CROOK_RECIPE_CATEGORY;
	}

	@Override
	public @Nullable Identifier getId() {
		// TODO - aaaaa
		return new Identifier("emi", "crooked_crooks/crook_multiplier/" + multiplier);
	}

	// TODO - Sneak in the block list there
	@Override
	public List<EmiIngredient> getInputs() {
		return List.of(EmiIngredient.of(CrookedCrooksMod.CROOKS));
	}

	@Override
	public List<EmiStack> getOutputs() {
		return List.of();
	}

	@Override
	public int getDisplayWidth() {
		return 16 * 8 + 2;
	}

	@Override
	public int getDisplayHeight() {
		return 16 * (1 + MathHelper.ceil(stackList.size() / 6)) + 2;
	}

	@Override
	public void addWidgets(WidgetHolder widgets) {
		int i = 0;
		widgets.addText(Text.literal(multiplier + "x").asOrderedText(), 0, 4, 0xFFFFFF, true);
		for (EmiStack stack : stackList) {
			widgets.addSlot(stack, 32 + (16 * (i % 6)), 16 * MathHelper.ceil(i / 6));
			i += 1;
		}
	}

}
