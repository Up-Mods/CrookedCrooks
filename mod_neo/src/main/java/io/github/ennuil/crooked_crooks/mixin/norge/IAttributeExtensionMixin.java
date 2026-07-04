package io.github.ennuil.crooked_crooks.mixin.norge;

import io.github.ennuil.crooked_crooks.item.CrookItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.common.extensions.IAttributeExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(IAttributeExtension.class)
public interface IAttributeExtensionMixin {
	@Inject(method = "getBaseId", at = @At("TAIL"), cancellable = true)
	private void considerBaseEntityReachAsBase(CallbackInfoReturnable<ResourceLocation> cir) {
		if ((Attribute) this == Attributes.BLOCK_INTERACTION_RANGE.value()) {
			cir.setReturnValue(CrookItem.BASE_BLOCK_REACH);
		}
	}
}
