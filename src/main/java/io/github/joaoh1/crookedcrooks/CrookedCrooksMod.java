package io.github.joaoh1.crookedcrooks;

import io.github.joaoh1.crookedcrooks.item.CrookItem;
import io.github.joaoh1.crookedcrooks.item.CrookMaterials;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CrookedCrooksMod implements ModInitializer {
	//The crook_effective tag, used for blocks which should have its drops multiplied by crooks.
	public static final Tag<Block> CROOK_EFFECTIVE = TagRegistry.block(new Identifier("crookedcrooks", "crook_effective"));
	//The crooks tag, used to list all the crooks and to apply the multiplier.
	public static final Tag<Item> CROOKS = TagRegistry.item(new Identifier("crookedcrooks", "crooks"));
	
	//The crook items, which will be registered.
	//Vanilla Crooks.
	public static final Item WOODEN_CROOK_ITEM = new CrookItem(CrookMaterials.WOOD, 0F, -1F, 0.2F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item STONE_CROOK_ITEM = new CrookItem(CrookMaterials.STONE, 0F, -1F, 0.25F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item IRON_CROOK_ITEM = new CrookItem(CrookMaterials.IRON, 0F, -1F, 0.5F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item DIAMOND_CROOK_ITEM = new CrookItem(CrookMaterials.DIAMOND, 0F, -1F, 0.625F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item GOLDEN_CROOK_ITEM = new CrookItem(CrookMaterials.GOLD, 0F, -1F, 0.2F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item NETHERITE_CROOK_ITEM = new CrookItem(CrookMaterials.NETHERITE, 0F, -1F, 0.75F, new Item.Settings().group(ItemGroup.TOOLS));
	//Tech Reborn Crooks.
	public static final Item BRONZE_CROOK_ITEM = new CrookItem(CrookMaterials.BRONZE, 0F, -1F, 0.55F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item RUBY_CROOK_ITEM = new CrookItem(CrookMaterials.RUBY, 0F, -1F, 0.6F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item SAPPHIRE_CROOK_ITEM = new CrookItem(CrookMaterials.SAPPHIRE, 0F, -1F, 0.625F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item PERIDOT_CROOK_ITEM = new CrookItem(CrookMaterials.PERIDOT, 0F, -1F, 0.575F, new Item.Settings().group(ItemGroup.TOOLS));
	//Adabranium Crooks.
	public static final Item VIBRANIUM_CROOK_ITEM = new CrookItem(CrookMaterials.VIBRANIUM, 0F, -1F, 0.75F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item ADAMANTIUM_CROOK_ITEM = new CrookItem(CrookMaterials.ADAMANTIUM, 0F, -1F, 0.85F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item NETHER_CROOK_ITEM = new CrookItem(CrookMaterials.NETHER, 0F, -1F, 0.5F, new Item.Settings().group(ItemGroup.TOOLS));
	//TODO - Astromine Crooks.

	@Override
	public void onInitialize() {
		FabricLoader fabricLoader = FabricLoader.getInstance();

		//Register all the crooks.
		Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "wooden_crook"), WOODEN_CROOK_ITEM);
		Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "stone_crook"), STONE_CROOK_ITEM);
		Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "iron_crook"), IRON_CROOK_ITEM);
		Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "diamond_crook"), DIAMOND_CROOK_ITEM);
		Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "golden_crook"), GOLDEN_CROOK_ITEM);
		Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "netherite_crook"), NETHERITE_CROOK_ITEM);
		if (fabricLoader.isModLoaded("techreborn")) {
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "bronze_crook"), BRONZE_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "ruby_crook"), RUBY_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "sapphire_crook"), SAPPHIRE_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "peridot_crook"), PERIDOT_CROOK_ITEM);
		}
		if (fabricLoader.isModLoaded("adabraniummod")) {
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "vibranium_crook"), VIBRANIUM_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "adamantium_crook"), ADAMANTIUM_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "nether_crook"), NETHER_CROOK_ITEM);
		}

		//Make Wooden Crooks usable as fuel. More mods should have this.
		FuelRegistry.INSTANCE.add(WOODEN_CROOK_ITEM, 200);
	}
}
