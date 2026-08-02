package io.github.ennuil.crooked_crooks.neo.data_maps;

import io.github.ennuil.crooked_crooks.CrookedCrooks;
import io.github.ennuil.crooked_crooks.crookdata.CrookData;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.datamaps.DataMapType;

public class CrookedDataMaps {
	public static final DataMapType<Block, CrookData> MULTIPLIES_DROPS = DataMapType.builder(
		CrookedCrooks.id("multiplies_drops"),
		Registries.BLOCK,
		CrookData.CODEC
	).build();
}
