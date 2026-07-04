package io.github.ennuil.crooked_crooks.block.data_maps;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record MultiplierData(int dropMultiplier) {
	public static final Codec<MultiplierData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
		Codec.intRange(1, 2048).fieldOf("drop_multiplier").forGetter(MultiplierData::dropMultiplier)
	).apply(instance, MultiplierData::new));
}
