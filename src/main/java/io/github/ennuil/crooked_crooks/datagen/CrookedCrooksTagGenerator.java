package io.github.ennuil.crooked_crooks.datagen;

import io.github.ennuil.crooked_crooks.CrookedCrooksMod;
import io.github.ennuil.crooked_crooks.items.CrookTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.HolderLookup;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class CrookedCrooksTagGenerator extends FabricTagProvider.ItemTagProvider {

	public CrookedCrooksTagGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
		super(output, completableFuture);
	}

	@Override
	protected void configure(HolderLookup.Provider arg) {
		getOrCreateTagBuilder(CrookTags.CROOKS)
			.add(
				CrookedCrooksMod.WOODEN_CROOK_ITEM,
				CrookedCrooksMod.STONE_CROOK_ITEM,
				CrookedCrooksMod.IRON_CROOK_ITEM,
				CrookedCrooksMod.DIAMOND_CROOK_ITEM,
				CrookedCrooksMod.GOLDEN_CROOK_ITEM,
				CrookedCrooksMod.NETHERITE_CROOK_ITEM
			)
			.addOptional(new Identifier(CrookedCrooksMod.MODID, "tr_bronze_crook"))
			.addOptional(new Identifier(CrookedCrooksMod.MODID, "ruby_crook"))
			.addOptional(new Identifier(CrookedCrooksMod.MODID, "sapphire_crook"))
			.addOptional(new Identifier(CrookedCrooksMod.MODID, "peridot_crook"))
			.addOptional(new Identifier(CrookedCrooksMod.MODID, "certus_quartz_crook"))
			.addOptional(new Identifier(CrookedCrooksMod.MODID, "nether_quartz_crook"))
			.addOptional(new Identifier(CrookedCrooksMod.MODID, "fluix_crook"))
			.addOptional(new Identifier(CrookedCrooksMod.MODID, "tin_crook"))
			.addOptional(new Identifier(CrookedCrooksMod.MODID, "copper_crook"))
			.addOptional(new Identifier(CrookedCrooksMod.MODID, "steel_crook"))
			.addOptional(new Identifier(CrookedCrooksMod.MODID, "lead_crook"))
			.addOptional(new Identifier(CrookedCrooksMod.MODID, "ir_bronze_crook"))
			.addOptional(new Identifier(CrookedCrooksMod.MODID, "silver_crook"))
			.addOptional(new Identifier(CrookedCrooksMod.MODID, "rose_gold_crook"))
			.addOptional(new Identifier(CrookedCrooksMod.MODID, "gilded_netherite_crook"));

		getOrCreateTagBuilder(CrookTags.QUARTZ_CROOKS)
			.addOptional(new Identifier(CrookedCrooksMod.MODID, "nether_quartz_crook"))
			.addOptional(new Identifier(CrookedCrooksMod.MODID, "certus_quartz_crook"));

		getOrCreateTagBuilder(ItemTags.PIGLIN_LOVED)
			.add(CrookedCrooksMod.GOLDEN_CROOK_ITEM);

		getOrCreateTagBuilder(CrookTags.Common.PERIDOTS)
			.addOptional(new Identifier("techreborn", "peridot_gem"));

	}
}
