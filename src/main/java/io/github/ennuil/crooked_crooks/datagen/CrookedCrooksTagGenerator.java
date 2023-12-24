package io.github.ennuil.crooked_crooks.datagen;

import io.github.ennuil.crooked_crooks.CrookedCrooksMod;
import io.github.ennuil.crooked_crooks.items.CrookTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
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
		this.getOrCreateTagBuilder(CrookTags.CROOKS)
			.add(
				CrookedCrooksMod.WOODEN_CROOK_ITEM,
				CrookedCrooksMod.STONE_CROOK_ITEM,
				CrookedCrooksMod.IRON_CROOK_ITEM,
				CrookedCrooksMod.DIAMOND_CROOK_ITEM,
				CrookedCrooksMod.GOLDEN_CROOK_ITEM,
				CrookedCrooksMod.NETHERITE_CROOK_ITEM
			)
			.addOptional(new Identifier(CrookedCrooksMod.MOD_ID, "tr_bronze_crook"))
			.addOptional(new Identifier(CrookedCrooksMod.MOD_ID, "ruby_crook"))
			.addOptional(new Identifier(CrookedCrooksMod.MOD_ID, "sapphire_crook"))
			.addOptional(new Identifier(CrookedCrooksMod.MOD_ID, "peridot_crook"))
			.addOptional(new Identifier(CrookedCrooksMod.MOD_ID, "certus_quartz_crook"))
			.addOptional(new Identifier(CrookedCrooksMod.MOD_ID, "nether_quartz_crook"))
			.addOptional(new Identifier(CrookedCrooksMod.MOD_ID, "fluix_crook"))
			.addOptional(new Identifier(CrookedCrooksMod.MOD_ID, "tin_crook"))
			.addOptional(new Identifier(CrookedCrooksMod.MOD_ID, "copper_crook"))
			.addOptional(new Identifier(CrookedCrooksMod.MOD_ID, "steel_crook"))
			.addOptional(new Identifier(CrookedCrooksMod.MOD_ID, "lead_crook"))
			.addOptional(new Identifier(CrookedCrooksMod.MOD_ID, "ir_bronze_crook"))
			.addOptional(new Identifier(CrookedCrooksMod.MOD_ID, "silver_crook"))
			.addOptional(new Identifier(CrookedCrooksMod.MOD_ID, "rose_gold_crook"))
			.addOptional(new Identifier(CrookedCrooksMod.MOD_ID, "gilded_netherite_crook"));

		this.getOrCreateTagBuilder(CrookTags.QUARTZ_CROOKS)
			.addOptional(new Identifier(CrookedCrooksMod.MOD_ID, "nether_quartz_crook"))
			.addOptional(new Identifier(CrookedCrooksMod.MOD_ID, "certus_quartz_crook"));

		this.getOrCreateTagBuilder(ItemTags.PIGLIN_LOVED)
			.add(CrookedCrooksMod.GOLDEN_CROOK_ITEM);

		this.getOrCreateTagBuilder(CrookTags.Common.PERIDOTS)
			.addOptional(new Identifier("techreborn", "peridot_gem"));

		this.getOrCreateTagBuilder(CrookTags.Common.IRON_NUGGETS)
			.add(Items.IRON_NUGGET);

		this.getOrCreateTagBuilder(CrookTags.Common.GOLD_NUGGETS)
			.add(Items.GOLD_NUGGET);
	}
}
