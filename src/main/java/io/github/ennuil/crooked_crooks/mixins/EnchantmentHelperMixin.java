package io.github.ennuil.crooked_crooks.mixins;

import io.github.ennuil.crooked_crooks.enchantments.ThornsCurseEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;
import java.util.List;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
    @Inject(
		method = "getPossibleEntries(ILnet/minecraft/item/ItemStack;Z)Ljava/util/List;",
        at = @At(
            value = "INVOKE",
            target = "net/minecraft/enchantment/Enchantment.isTreasure()Z"
        ),
        locals = LocalCapture.CAPTURE_FAILHARD
    )
    private static void avoidEnchantmentTypeIssues(int power, ItemStack stack, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir, List<EnchantmentLevelEntry> list, Item item, boolean bl, Iterator<?> var6, Enchantment enchantment) {
        if (enchantment instanceof ThornsCurseEnchantment thornsCurseEnchantment) {
            if ((!enchantment.isTreasure() || treasureAllowed) && (bl || enchantment.isAcceptableItem(stack))) {
                // The Curse of Thorns enchantment only has one level, so we don't have to iterate through the levels
                list.add(new EnchantmentLevelEntry(enchantment, 1));
            }
            thornsCurseEnchantment.avoidNPE();
        }
    }

	// TODO - Use AE2's shenanigans instead of a duplicate piece of code
	@Inject(method = "getLevel", at = @At("RETURN"), cancellable = true)
	private static void ae2Shenanigans(Enchantment enchantment, ItemStack stack, CallbackInfoReturnable<Integer> cir) {
		if (cir.getReturnValueI() == 0 && enchantment == Enchantments.FORTUNE) {
			if (Registries.ITEM.getId(stack.getItem()).equals(new Identifier("crooked_crooks", "fluix_crook"))) {
				cir.setReturnValue(1);
			}
		}
	}
}
