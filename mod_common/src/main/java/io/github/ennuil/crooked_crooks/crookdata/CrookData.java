package io.github.ennuil.crooked_crooks.crookdata;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.ennuil.crooked_crooks.CrookedCrooks;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public record CrookData(int bonusRolls) {
	public static final Codec<CrookData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
		Codec.INT.optionalFieldOf("bonus_rolls", 1).forGetter(CrookData::bonusRolls)
	).apply(instance, CrookData::new));

	public static final ResourceKey<Registry<CrookData>> REGISTRY_KEY = ResourceKey.createRegistryKey(CrookedCrooks.id("crook_data"));

	public static Optional<CrookData> getFor(RegistryAccess registryAccess, BlockState blockState) {
		var newKey = convertKey(blockState.getBlock());
		var registry = registryAccess.lookupOrThrow(CrookData.REGISTRY_KEY).get(newKey);
		throw new UnsupportedOperationException(); // TODO
	}

	@SuppressWarnings("deprecation")
	public static ResourceKey<CrookData> convertKey(Block block) {
		return convertKey(block.builtInRegistryHolder().key());
	}

	public static ResourceKey<CrookData> convertKey(ResourceKey<Block> blockResourceKey) {
		return ResourceKey.create(REGISTRY_KEY, blockResourceKey.identifier());
	}
}
