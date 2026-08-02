package io.github.ennuil.crooked_crooks.neo.mixin;

import io.github.ennuil.crooked_crooks.item.CrookItem;
import io.github.ennuil.crooked_crooks.neo.itemability.CrookedItemAbilities;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemInstance;
import net.neoforged.neoforge.common.ItemAbility;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CrookItem.class)
public abstract class CrookItemMixin extends Item {

	public CrookItemMixin(Properties properties) {
		super(properties);
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean canPerformAction(ItemInstance stack, ItemAbility itemAbility) {
		return itemAbility == CrookedItemAbilities.CROOK || super.canPerformAction(stack, itemAbility);
	}
}
