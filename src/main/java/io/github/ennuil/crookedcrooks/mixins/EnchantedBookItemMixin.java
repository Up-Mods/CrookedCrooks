package io.github.ennuil.crookedcrooks.mixins;

import java.util.Iterator;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import io.github.ennuil.crookedcrooks.enchantments.ThornsCurseEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

@Mixin(EnchantedBookItem.class)
public class EnchantedBookItemMixin {
    @Shadow
    public static ItemStack forEnchantment(EnchantmentLevelEntry info) { return null; }

    @Inject(
        at = @At(
            value = "FIELD",
            target = "net/minecraft/enchantment/Enchantment.type:Lnet/minecraft/enchantment/EnchantmentTarget;"
        ),
        method = "appendStacks(Lnet/minecraft/item/ItemGroup;Lnet/minecraft/util/collection/DefaultedList;)V",
        locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void appendThornsCurseBook(ItemGroup group, DefaultedList<ItemStack> stacks, CallbackInfo ci, Iterator<?> var3, Enchantment enchantment) {
        if (group == ItemGroup.TOOLS || group == ItemGroup.SEARCH) {
            if (enchantment instanceof ThornsCurseEnchantment) {
                // The Curse of Thorns enchantment only has one level, so we don't have to iterate through the levels
                stacks.add(forEnchantment(new EnchantmentLevelEntry(enchantment, 1)));
            }
        }
    }
}
