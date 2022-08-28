package io.github.ennuil.crooked_crooks.emi;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.SlotWidget;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class CrookDropEfficiencyEmiRecipe implements EmiRecipe {
	private final List<EmiStack> stackList = new ArrayList<>();
	private final int multiplier;
	// TODO - Memorizing the pages and such is nice and stuff; One little problem: it should be promptly forgotten once switching screens
	private int rowOffset;
	private int maxPages;
	private int maxRowCount;

	public CrookDropEfficiencyEmiRecipe(int multiplier, List<Block> blockList) {
		blockList.stream().forEach(block -> this.stackList.add(EmiStack.of(block)));
		this.multiplier = multiplier;
		this.rowOffset = 0;
		this.maxPages = 0;
		this.maxRowCount = 1;
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
		int multiplierTextWidth = client.textRenderer.getWidth(multiplierText);
		int padding = MathHelper.ceil((this.getDisplayWidth() - multiplierTextWidth) / 2);
		widgets.addText(multiplierText, padding, 4, 0xFFFFFF, true);
		int totalRows = MathHelper.ceilDiv(this.stackList.size(), 8);
		int maxRows = MathHelper.floorDiv(widgets.getHeight(), 18) - 1;

		this.maxPages = MathHelper.ceilDiv(this.stackList.size(), maxRows);
		this.recalculateSlotOffset(maxRows, totalRows);

		if (totalRows > maxRows) {
			widgets.addButton(2, 2, 12, 12, 0, 64, () -> true, (mouseX, mouseY, button) -> {
				this.rowOffset = this.rowOffset - maxRows < 0 ? MathHelper.floorDiv(totalRows, maxRows) * maxRows : this.rowOffset - maxRows;
				System.out.println(rowOffset);
			});
			widgets.addButton(this.getDisplayWidth() - 12 - 2, 2, 12, 12, 12, 64, () -> true, (mouseX, mouseY, button) -> {
				this.rowOffset = this.rowOffset + maxRows >= totalRows ? 0 : this.rowOffset + maxRows;
				System.out.println(rowOffset);
			});
		}
		for (int i = 0; i < maxRows * 8; i++) {
			widgets.add(new DropEfficiencyPageSlotWidget(18 * (i % 8), 18 + 18 * MathHelper.ceil(i / 8), i));
		}
	}

	private void recalculateSlotOffset(int newMaxRowCount, int totalRows) {
		this.rowOffset = MathHelper.floorDiv(this.rowOffset, this.maxRowCount) * newMaxRowCount;
		int ceiling = MathHelper.floorDiv(totalRows, newMaxRowCount) * newMaxRowCount;
		this.rowOffset = Math.min(this.rowOffset, ceiling);
		System.out.println(this.rowOffset);
		this.maxRowCount = newMaxRowCount;
	}

	private class DropEfficiencyPageSlotWidget extends SlotWidget {
		private int slotOffset;

		private DropEfficiencyPageSlotWidget(int x, int y, int slotOffset) {
			super(EmiStack.EMPTY, x, y);
			this.slotOffset = slotOffset;
		}

		@Override
		protected EmiIngredient getStack() {
			var slot = (rowOffset * 8) + slotOffset;
			return slot >= stackList.size() ? EmiStack.EMPTY : stackList.get((rowOffset * 8) + slotOffset);
		}

		// TODO - aaaaaaaaaaaaa
		@Override
		public EmiRecipe getRecipe() {
			return super.getRecipe();
		}

		@Override
		public void render(MatrixStack matrices, int mouseX, int mouseY, float tickDelta) {
			if (!this.getStack().isEmpty()) {
				super.render(matrices, mouseX, mouseY, tickDelta);
			}
		}
	}
}
