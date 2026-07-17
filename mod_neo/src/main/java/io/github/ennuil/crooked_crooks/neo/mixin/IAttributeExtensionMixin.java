package io.github.ennuil.crooked_crooks.neo.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.ennuil.crooked_crooks.item.CrookItem;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.common.extensions.IAttributeExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(IAttributeExtension.class)
public interface IAttributeExtensionMixin {
	@Shadow
	private Attribute self() {
		throw new UnsupportedOperationException();
	}

	@ModifyReturnValue(method = "getBaseId", at = @At("RETURN"))
	private Identifier considerBaseEntityReachAsBase(Identifier original) {
		if (this.self() == Attributes.BLOCK_INTERACTION_RANGE.value()) {
			return CrookItem.BASE_BLOCK_REACH;
		}

		return original;
	}
}
