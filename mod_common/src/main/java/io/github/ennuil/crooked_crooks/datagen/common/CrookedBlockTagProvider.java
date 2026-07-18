package io.github.ennuil.crooked_crooks.datagen.common;

import dev.upcraft.sparkweave.api.datagen.provider.common.SparkweaveBlockTagProvider;
import io.github.ennuil.crooked_crooks.CrookedCrooks;
import io.github.ennuil.crooked_crooks.data.CrookedTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class CrookedBlockTagProvider extends SparkweaveBlockTagProvider {

	public CrookedBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(output, CrookedCrooks.MOD_NAMESPACE, lookupProvider);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		tag(CrookedTags.Blocks.MINEABLE_WITH_CROOK, "Mineable with Crook")
			.addExistingTag(BlockTags.LEAVES)
			.add(Blocks.SHORT_GRASS)
			.add(Blocks.SHORT_DRY_GRASS)
			.add(Blocks.TALL_GRASS)
			.add(Blocks.TALL_DRY_GRASS)
			.add(Blocks.FERN)
			.add(Blocks.LARGE_FERN)
			.add(Blocks.SEAGRASS)
			.add(Blocks.TALL_SEAGRASS);

		tag(CrookedTags.Blocks.MULTIPLE_DROOPS_WHEN_CROOKED, "Multiple Drops when mined with Crook")
			.addTag(CrookedTags.Blocks.MINEABLE_WITH_CROOK);
	}
}
