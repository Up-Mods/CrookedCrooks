package io.github.ennuil.crooked_crooks.events;

import io.github.ennuil.crooked_crooks.CrookedCrooksMod;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;

// This event handles the drop-multiplying part of the crook
public class MultiplyDropsEvent {
	public static void registerEvent() {
		PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, entity) -> {
			if (player.isCreative()) return;

			var equippedStack = player.getMainHandStack();
			if (equippedStack.isIn(CrookedCrooksMod.CROOKS)) {
				if (equippedStack.isSuitableFor(state) && !EnchantmentHelper.hasSilkTouch(equippedStack)) {
					int multiplier = CrookedCrooksMod.CROOK_EFFECTIVE.get(state.getBlock()).orElse(0);
					for (int i = 1; i < multiplier; i++) {
						Block.dropStacks(state, world, pos, entity, player, equippedStack);
					}
				}
			}
		});
	}
}
