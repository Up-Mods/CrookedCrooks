package io.github.ennuil.crookedcrooks;

import io.github.cottonmc.mcdict.api.Dict;
import io.github.cottonmc.mcdict.api.DictManager;
import io.github.ennuil.crookedcrooks.enchantments.ThornsCurseEnchantment;
import io.github.ennuil.crookedcrooks.events.MultiplyDropsEvent;
import io.github.ennuil.crookedcrooks.item.CrookItem;
import io.github.ennuil.crookedcrooks.item.CrookMaterials;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CrookedCrooksMod implements ModInitializer {
	// The crook_effective tag, used for blocks which should have its drops multiplied by crooks
	public static final Dict<Block, Integer> CROOK_EFFECTIVE = DictManager.DATA_PACK.registerBlockDict(new Identifier("crookedcrooks", "crook_effective"), Integer.class);
	// The crooks tag, used to list all the crooks and to apply the multiplier
	public static final Tag<Item> CROOKS = TagFactory.ITEM.create(new Identifier("crookedcrooks", "crooks"));
	
	// The crook items, which are going to be registered
	// Vanilla Crooks
	public static final Item WOODEN_CROOK_ITEM = new CrookItem(CrookMaterials.WOOD, 0F, -3F, 0.4F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item STONE_CROOK_ITEM = new CrookItem(CrookMaterials.STONE, 0F, -3F, 0.5F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item IRON_CROOK_ITEM = new CrookItem(CrookMaterials.IRON, 0F, -3F, 0.8F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item DIAMOND_CROOK_ITEM = new CrookItem(CrookMaterials.DIAMOND, 0F, -3F, 1.0F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item GOLDEN_CROOK_ITEM = new CrookItem(CrookMaterials.GOLD, 0F, -3F, 0.4F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item NETHERITE_CROOK_ITEM = new CrookItem(CrookMaterials.NETHERITE, 0F, -3F, 1.2F, new Item.Settings().group(ItemGroup.TOOLS).fireproof());
	// Tech Reborn Crooks
	public static final Item TECH_REBORN_BRONZE_CROOK_ITEM = new CrookItem(CrookMaterials.TECH_REBORN_BRONZE, 0F, -3F, 0.85F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item RUBY_CROOK_ITEM = new CrookItem(CrookMaterials.RUBY, 0F, -3F, 0.9F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item SAPPHIRE_CROOK_ITEM = new CrookItem(CrookMaterials.SAPPHIRE, 0F, -3F, 1.0F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item PERIDOT_CROOK_ITEM = new CrookItem(CrookMaterials.PERIDOT, 0F, -3F, 0.875F, new Item.Settings().group(ItemGroup.TOOLS));
	// Applied Energistics 2 Crooks
	public static final Item CERTUS_QUARTZ_CROOK_ITEM = new CrookItem(CrookMaterials.IRON, 0F, -3F, 0.8F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item NETHER_QUARTZ_CROOK_ITEM = new CrookItem(CrookMaterials.NETHER_QUARTZ, 0F, -3F, 0.8F, new Item.Settings().group(ItemGroup.TOOLS));
	// Industrial Revolution Crooks
	public static final Item TIN_CROOK_ITEM = new CrookItem(CrookMaterials.TIN, 0F, -3F, 0.5F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item COPPER_CROOK_ITEM = new CrookItem(CrookMaterials.COPPER, 0F, -3F, 0.8F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item STEEL_CROOK_ITEM = new CrookItem(CrookMaterials.STEEL, 0F, -3F, 0.9F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item LEAD_CROOK_ITEM = new CrookItem(CrookMaterials.LEAD, 0F, -3F, 0.75F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item INDREV_BRONZE_CROOK_ITEM = new CrookItem(CrookMaterials.INDREV_BRONZE, 0F, -3F, 0.85F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item SILVER_CROOK_ITEM = new CrookItem(CrookMaterials.SILVER, 0F, -3F, 0.8F, new Item.Settings().group(ItemGroup.TOOLS));
	/*
	// Better End Crooks
	public static final Item THALLASIUM_CROOK_ITEM = new CrookItem(CrookMaterials.THALLASIUM, 0F, -3F, 0.8F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item TERMINITE_CROOK_ITEM = new CrookItem(CrookMaterials.TERMINITE, 0F, -3F, 1.0F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item AETERNIUM_CROOK_ITEM = new CrookItem(CrookMaterials.AETERNIUM, 0F, -3F, 1.3F, new Item.Settings().group(ItemGroup.TOOLS));
	*/
	// Additional Additions Crooks
	public static final Item ROSE_GOLD_CROOK_ITEM = new CrookItem(CrookMaterials.ROSE_GOLD, 0F, -3F, 1.3F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item GILDED_NETHERITE_CROOK_ITEM = new CrookItem(CrookMaterials.GILDED_NETHERITE, 0F, -3F, 1.3F, new Item.Settings().group(ItemGroup.TOOLS));

	// The crook enchantments
	public static final Enchantment THORNS_CURSE_ENCHANTMENT = new ThornsCurseEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND);

	@Override
	public void onInitialize() {
		FabricLoader fabricLoader = FabricLoader.getInstance();

		// Register all the crooks
		Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "wooden_crook"), WOODEN_CROOK_ITEM);
		Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "stone_crook"), STONE_CROOK_ITEM);
		Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "iron_crook"), IRON_CROOK_ITEM);
		Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "diamond_crook"), DIAMOND_CROOK_ITEM);
		Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "golden_crook"), GOLDEN_CROOK_ITEM);
		Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "netherite_crook"), NETHERITE_CROOK_ITEM);

		if (fabricLoader.isModLoaded("techreborn")) {
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "ruby_crook"), RUBY_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "sapphire_crook"), SAPPHIRE_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "peridot_crook"), PERIDOT_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "tr_bronze_crook"), TECH_REBORN_BRONZE_CROOK_ITEM);
		}
		
		if (fabricLoader.isModLoaded("appliedenergistics2")) {
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "certus_quartz_crook"), CERTUS_QUARTZ_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "nether_quartz_crook"), NETHER_QUARTZ_CROOK_ITEM);
		}
		
		if (fabricLoader.isModLoaded("indrev")) {
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "tin_crook"), TIN_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "copper_crook"), COPPER_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "steel_crook"), STEEL_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "lead_crook"), LEAD_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "ir_bronze_crook"), INDREV_BRONZE_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "silver_crook"), SILVER_CROOK_ITEM);
		}

		/*
		if (fabricLoader.isModLoaded("betterend")) {
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "thallasium_crook"), THALLASIUM_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "terminite_crook"), TERMINITE_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "aeternium_crook"), AETERNIUM_CROOK_ITEM);
		}
		*/

		if (fabricLoader.isModLoaded("additionaladditions")) {
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "rose_gold_crook"), ROSE_GOLD_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "gilded_netherite_crook"), GILDED_NETHERITE_CROOK_ITEM);
		}

		// Register enchantments
		Registry.register(Registry.ENCHANTMENT, "crookedcrooks:thorns_curse", THORNS_CURSE_ENCHANTMENT);

		// Register the drop-multiplying event
		MultiplyDropsEvent.registerEvent();

		// Make Wooden Crooks usable as fuel. More mods should have this
		FuelRegistry.INSTANCE.add(WOODEN_CROOK_ITEM, 200);
	}
}
