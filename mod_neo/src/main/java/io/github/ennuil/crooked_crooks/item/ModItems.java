package io.github.ennuil.crooked_crooks.item;

import io.github.ennuil.crooked_crooks.utils.ModUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.Items.createItems(ModUtils.MOD_NAMESPACE);

	public static final DeferredItem<CrookItem> WOODEN_CROOK = ITEMS.registerItem(
		"wooden_crook",
		properties -> new CrookItem(ToolMaterial.WOOD, 0, -3, 0.4F, properties),
		new Item.Properties()
	);

	public static final DeferredItem<CrookItem> STONE_CROOK = ITEMS.registerItem(
		"stone_crook",
		properties -> new CrookItem(ToolMaterial.STONE, 0, -3, 0.4F, properties),
		new Item.Properties()
	);

	public static final DeferredItem<CrookItem> BONE_CROOK = ITEMS.registerItem(
		"bone_crook",
		properties -> new CrookItem(ModToolMaterials.BONE, 0, -3, 0.5F, properties),
		new Item.Properties()
	);

	public static final DeferredItem<CrookItem> IRON_CROOK = ITEMS.registerItem(
		"iron_crook",
		properties -> new CrookItem(ToolMaterial.IRON, 0, -3, 0.7F, properties),
		new Item.Properties()
	);
}
