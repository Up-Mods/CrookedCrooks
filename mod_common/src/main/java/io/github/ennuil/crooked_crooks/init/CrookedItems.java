package io.github.ennuil.crooked_crooks.init;

import dev.upcraft.sparkweave.api.registry.RegistryHandler;
import dev.upcraft.sparkweave.api.registry.RegistrySupplier;
import dev.upcraft.sparkweave.api.registry.item.ItemRegistryHandler;
import io.github.ennuil.crooked_crooks.CrookedCrooks;
import io.github.ennuil.crooked_crooks.data.CrookedToolMaterials;
import io.github.ennuil.crooked_crooks.item.CrookItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

public class CrookedItems {

	public static final ItemRegistryHandler ITEMS = RegistryHandler.items(CrookedCrooks.MOD_NAMESPACE);

	public static final RegistrySupplier<Item> WOODEN_CROOK = ITEMS.register("wooden_crook", properties -> new CrookItem(ToolMaterial.WOOD, 0, -3, 0.4F, properties), Item.Properties::new);
	public static final RegistrySupplier<Item> STONE_CROOK = ITEMS.register("stone_crook", properties -> new CrookItem(ToolMaterial.STONE, 0, -3, 0.4F, properties), Item.Properties::new);
	public static final RegistrySupplier<Item> IRON_CROOK = ITEMS.register("iron_crook", properties -> new CrookItem(ToolMaterial.IRON, 0, -3, 0.7F, properties), Item.Properties::new);

	public static final RegistrySupplier<Item> BONE_CROOK = ITEMS.register("bone_crook", properties -> new CrookItem(CrookedToolMaterials.BONE, 0, -3, 0.5F, properties), Item.Properties::new);

}
