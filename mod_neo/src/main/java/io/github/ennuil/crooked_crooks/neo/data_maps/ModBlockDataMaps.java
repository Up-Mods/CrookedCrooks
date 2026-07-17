package io.github.ennuil.crooked_crooks.neo.data_maps;

import io.github.ennuil.crooked_crooks.CrookedCrooks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.datamaps.DataMapType;

public class ModBlockDataMaps {
	public static final DataMapType<Block, MultiplierData> MULTIPLIES_DROPS = DataMapType.builder(
		CrookedCrooks.id("multiplies_drops"),
		Registries.BLOCK,
		MultiplierData.CODEC
	).synced(MultiplierData.CODEC, false).build();
}
