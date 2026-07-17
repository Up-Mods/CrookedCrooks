package io.github.ennuil.crooked_crooks.datagen.client;

import dev.upcraft.sparkweave.api.datagen.ContextAwarePackOutput;
import dev.upcraft.sparkweave.api.datagen.provider.client.SparkweaveModelProvider;
import io.github.ennuil.crooked_crooks.CrookedCrooks;
import io.github.ennuil.crooked_crooks.init.CrookedItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.world.item.Item;

import java.util.Optional;
import java.util.function.Supplier;

public class CrookedModelProvider extends SparkweaveModelProvider {

	private static final ModelTemplate FLAT_HANDHELD_CROOK_ITEM = new ModelTemplate(
		Optional.of(CrookedCrooks.id("item/handheld_crook")), Optional.empty(), TextureSlot.LAYER0
	);

	public CrookedModelProvider(ContextAwarePackOutput output) {
		super(output);
	}

	@Override
	protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
		generateCrook(itemModels, CrookedItems.BONE_CROOK);
		generateCrook(itemModels, CrookedItems.WOODEN_CROOK);
		generateCrook(itemModels, CrookedItems.STONE_CROOK);
		generateCrook(itemModels, CrookedItems.IRON_CROOK);
	}

	public static void generateCrook(ItemModelGenerators itemModels, Supplier<Item> item) {
		var unbaked = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item.get(), FLAT_HANDHELD_CROOK_ITEM));
		var unbaked2 = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item.get(), "_long", FLAT_HANDHELD_CROOK_ITEM));
		var conditionalUnbaked = ItemModelUtils.conditional(ItemModelUtils.isUsingItem(), unbaked, unbaked2);
		itemModels.getItemModelOutput().accept(item.get(), ItemModelGenerators.createFlatModelDispatch(unbaked, conditionalUnbaked));
	}
}
