package io.github.joaoh1.crookedcrooks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.joaoh1.crookedcrooks.CrookedCrooksMod;
import io.github.joaoh1.crookedcrooks.item.CrookItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

//This mixin handles the multiplication of drops by a crook.
@Mixin(Block.class)
public abstract class BlockMixin {
	@Shadow
	public static void dropStacks(BlockState state, World world, BlockPos pos, BlockEntity blockEntity, Entity entity, ItemStack stack) {}
	
	@Inject(at = @At("TAIL"), method = "afterBreak(Lnet/minecraft/world/World;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/item/ItemStack;)V")
	public void multiplyDrops(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack stack, CallbackInfo info) {
		if (stack.getItem() instanceof CrookItem) {
			if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
				if (state.isIn(CrookedCrooksMod.CROOK_EFFECTIVE)) {
					for (int i = 0; i < 5; i++) {
						dropStacks(state, world, pos, blockEntity, player, stack);	
					}
				}
			}
		}
	}
}
