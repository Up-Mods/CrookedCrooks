package io.github.ennuil.crooked_crooks.mixins;

import java.util.Iterator;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.screen.RecipeScreen;
import io.github.ennuil.crooked_crooks.emi.CrookDropEfficiencyEmiRecipe;

@Mixin(RecipeScreen.class)
public abstract class RecipeScreenMixin {
	@Unique
	private boolean crCr$dontPayAttentionToThisSetPage = false;

	@Inject(
		method = "init()V",
		at = @At(
			value = "INVOKE",
			target = "Ldev/emi/emi/screen/RecipeScreen;setPage(III)V"
		)
	)
	private void ignoreTheInitSetPage(CallbackInfo ci) {
		this.crCr$dontPayAttentionToThisSetPage = true;
	}

	@Inject(
		method = "setPage(III)V",
		at = @At(
			value = "INVOKE",
			// Apache Commons's Compress module? Why not Google's more generic one? Or heck, Java's diamond?
			target = "Lorg/apache/commons/compress/utils/Lists;newArrayList()Ljava/util/ArrayList;",
			ordinal = 0
		),
		locals = LocalCapture.CAPTURE_FAILHARD,
		remap = false
	)
	private void resetPageOnSetPage(int tp, int t, int p, CallbackInfo ci, boolean snapTabPage, List<EmiRecipe> recipes, int width, int off, Iterator<EmiRecipe> var8, EmiRecipe r) {
		if (r instanceof CrookDropEfficiencyEmiRecipe crookRecipe) {
			if (!this.crCr$dontPayAttentionToThisSetPage) {
				crookRecipe.resetPages();
			} else {
				this.crCr$dontPayAttentionToThisSetPage = false;
			}
		}
	}
}
