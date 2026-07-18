package io.github.ennuil.crooked_crooks.mixin.fabric;

import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
//	@Inject(
//		method = "addModifierTooltip",
//		at = @At(
//			value = "INVOKE",
//			target = "Lnet/minecraft/world/entity/ai/attributes/AttributeModifier;is(Lnet/minecraft/resources/ResourceLocation;)Z",
//			ordinal = 0
//		)
//	)
//	private void fancifyBaseRangeModifiers(Consumer<Component> tooltipAdder, Player player, Holder<Attribute> attribute, AttributeModifier modifier, CallbackInfo ci, @Local LocalDoubleRef d, @Local LocalBooleanRef bl) {
//		// player != null is always true here
//		if (modifier.is(CrookItem.BASE_BLOCK_REACH)) {
//			d.set(d.get() + player.getAttributeBaseValue(Attributes.BLOCK_INTERACTION_RANGE));
//			bl.set(true);
//		} else if (modifier.is(CrookItem.BASE_ENTITY_REACH)) {
//			d.set(d.get() + player.getAttributeBaseValue(Attributes.ENTITY_INTERACTION_RANGE));
//			bl.set(true);
//		}
//	}
}
