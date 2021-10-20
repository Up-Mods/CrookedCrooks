package io.github.ennuil.crookedcrooks.enchantments;

import io.github.ennuil.crookedcrooks.item.CrookItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class ThornsCurseEnchantment extends Enchantment {
    public ThornsCurseEnchantment(Rarity weight, EquipmentSlot... slotTypes) {
        super(weight, null, slotTypes);
    }

    @Override
    public int getMinPower(int level) {
        return 25;
    }

    @Override
    public int getMaxPower(int level) {
        return 50;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
    
    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof CrookItem;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public boolean isCursed() {
        return true;
    }
}
