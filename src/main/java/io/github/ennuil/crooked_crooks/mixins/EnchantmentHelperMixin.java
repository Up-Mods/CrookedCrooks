package io.github.ennuil.crooked_crooks.mixins;

import java.util.Iterator;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import io.github.ennuil.crooked_crooks.enchantments.ThornsCurseEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
    @Inject(
        at = @At(
            value = "INVOKE",
            target = "net/minecraft/enchantment/Enchantment.isTreasure()Z"
        ),
        method = "getPossibleEntries(ILnet/minecraft/item/ItemStack;Z)Ljava/util/List;",
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
}
