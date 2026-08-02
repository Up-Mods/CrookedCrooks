package io.github.ennuil.crooked_crooks.neo.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import io.github.ennuil.crooked_crooks.data.CrookedTags;
import io.github.ennuil.crooked_crooks.neo.data_maps.CrookedDataMaps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//TODO move to event
@Mixin(ServerPlayerGameMode.class)
public abstract class ServerPlayerGameModeMixin {
	@Shadow
	protected ServerLevel level;

	@Shadow
	@Final
	protected ServerPlayer player;

	@Inject(
		method = "destroyBlock",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/level/block/Block;playerDestroy(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/item/ItemStack;)V")
	)
	private void multiplyDrops(BlockPos pos, CallbackInfoReturnable<Boolean> cir, @Local(ordinal = 0) BlockEntity blockEntity, @Local Block block, @Local(ordinal = 0) ItemStack stack, @Local(ordinal = 1) ItemStack stackCopy, @Local(ordinal = 1) BlockState destroyedState) {
		if (stack.is(CrookedTags.Items.CROOKS)) {
			var data = destroyedState.getData(CrookedDataMaps.MULTIPLIES_DROPS);
			if (data != null) {
				var lookup = this.level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);

				if (stack.getEnchantmentLevel(lookup.getOrThrow(Enchantments.SILK_TOUCH)) == 0) {
					for (int i = 0; i < data.bonusRolls() - 1; i++) {
						Block.dropResources(destroyedState, this.level, pos, blockEntity, this.player, stackCopy);
					}
				}
			}
		}
	}
}
