package io.github.ennuil.crookedcrooks.events;

import io.github.ennuil.crookedcrooks.CrookedCrooksMod;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

// This event handles the drop-multiplying part of the crook
public class MultiplyDropsEvent {
	public static void registerEvent() {
		PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, entity) -> {
			if (player.isCreative()) return;
			ItemStack equippedStack = player.getStackInHand(Hand.MAIN_HAND);
			if (equippedStack.isIn(CrookedCrooksMod.CROOKS)) {
				if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, equippedStack) == 0) {
					if (equippedStack.isSuitableFor(state)) {
						int multiplier = CrookedCrooksMod.CROOK_EFFECTIVE.get(state.getBlock()).intValue();
						for (int i = 1; i < multiplier; i++) {
							Block.dropStacks(state, world, pos, entity, player, equippedStack);	
						}
					}
				}
			}
		});
	}
}
