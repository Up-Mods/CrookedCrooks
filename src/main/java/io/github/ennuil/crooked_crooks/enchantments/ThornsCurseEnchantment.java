package io.github.ennuil.crooked_crooks.enchantments;

import io.github.ennuil.crooked_crooks.items.CrookItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class ThornsCurseEnchantment extends Enchantment {
    private boolean npeLock;

    public ThornsCurseEnchantment(Rarity weight, EquipmentSlot... slotTypes) {
        super(weight, null, slotTypes);
        this.npeLock = false;
    }

    public void avoidNPE() {
        this.npeLock = true;
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

    @Override
    public boolean isAvailableForRandomSelection() {
        if (this.npeLock) {
            this.npeLock = false;
            return false;
        }

        return super.isAvailableForRandomSelection();
    }
}
