package io.github.joaoh1.crookedcrooks;

import io.github.joaoh1.crookedcrooks.item.CrookItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolMaterials;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CrookedCrooksMod implements ModInitializer {
	//The crook_effective tag, used for blocks which should have its drops multiplied by crooks.
	public static final Tag<Block> CROOK_EFFECTIVE = TagRegistry.block(new Identifier("crookedcrooks", "crook_effective"));
	
	//The crook items, which will be registered.
	public static final Item WOODEN_CROOK_ITEM = new CrookItem(ToolMaterials.WOOD, 0F, -1F, 0.2F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item STONE_CROOK_ITEM = new CrookItem(ToolMaterials.STONE, 0F, -1F, 0.25F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item IRON_CROOK_ITEM = new CrookItem(ToolMaterials.IRON, 0F, -1F, 0.5F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item DIAMOND_CROOK_ITEM = new CrookItem(ToolMaterials.DIAMOND, 0F, -1F, 0.625F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item GOLDEN_CROOK_ITEM = new CrookItem(ToolMaterials.GOLD, 0F, -1F, 0.2F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item NETHERITE_CROOK_ITEM = new CrookItem(ToolMaterials.NETHERITE, 0F, -1F, 0.75F, new Item.Settings().group(ItemGroup.TOOLS));
	
	@Override
	public void onInitialize() {
		//Register all the crooks.
		Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "wooden_crook"), WOODEN_CROOK_ITEM);
		Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "stone_crook"), STONE_CROOK_ITEM);
		Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "iron_crook"), IRON_CROOK_ITEM);
		Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "diamond_crook"), DIAMOND_CROOK_ITEM);
		Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "golden_crook"), GOLDEN_CROOK_ITEM);
		Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "netherite_crook"), NETHERITE_CROOK_ITEM);

		//Make Wooden Crooks usable as fuel. More mods should have this.
		FuelRegistry.INSTANCE.add(WOODEN_CROOK_ITEM, 200);
	}
}
