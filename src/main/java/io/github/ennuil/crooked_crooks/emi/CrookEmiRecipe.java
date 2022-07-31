package io.github.ennuil.crooked_crooks.emi;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.recipe.Ingredient;
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
		return new Identifier("emi", "crooked_crooks/crook_drop_efficiency/" + multiplier);
	}

	@Override
	public List<EmiIngredient> getInputs() {
		return this.stackList.stream().map(stack -> EmiIngredient.of(Ingredient.ofStacks(stack.getItemStack()))).toList();
	}

	@Override
	public List<EmiStack> getOutputs() {
		return List.of();
	}

	@Override
	public boolean supportsRecipeTree() {
		return false;
	}

	@Override
	public int getDisplayWidth() {
		return 18 * 8;
	}

	@Override
	public int getDisplayHeight() {
		return 18 * (1 + MathHelper.ceilDiv(stackList.size(), 6)) + 2;
	}

	// TODO - Implement pages; That's how EMI handles massive lists
	@Override
	public void addWidgets(WidgetHolder widgets) {
		var client = MinecraftClient.getInstance();
		var multiplierText = Text.translatable("emi.recipe.crooked_crooks.crook_efficiency.drop_multiplier", multiplier).asOrderedText();
		var multiplierTextWidth = client.textRenderer.getWidth(multiplierText);
		var padding = MathHelper.ceil((this.getDisplayWidth() - multiplierTextWidth) / 2);
		widgets.addText(multiplierText, padding, 4, 0xFFFFFF, true);
		int i = 0;
		widgets.addButton(2, 2, 12, 12, 0, 64, () -> true, (mouseX, mouseY, button) -> {});
		widgets.addButton(this.getDisplayWidth() - 12 - 2, 2, 12, 12, 12, 64, () -> true, (mouseX, mouseY, button) -> {});
		Math.min(MathHelper.ceil(i / 8), MathHelper.ceil(widgets.getHeight() / 18));
		System.out.println(MathHelper.ceil(this.stackList.size() / 8) + 1);
		System.out.println(MathHelper.floorDiv(widgets.getHeight(), 18) - 1);
		for (EmiStack stack : stackList) {
			widgets.addSlot(stack, 18 * (i % 8), 18 + 18 * MathHelper.ceil(i / 8));
			i += 1;
		}
	}

}
