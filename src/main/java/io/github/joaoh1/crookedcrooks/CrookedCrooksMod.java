package io.github.joaoh1.crookedcrooks;

import io.github.cottonmc.mcdict.api.Dict;
import io.github.cottonmc.mcdict.api.DictManager;
import io.github.joaoh1.crookedcrooks.item.CrookItem;
import io.github.joaoh1.crookedcrooks.item.CrookMaterials;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CrookedCrooksMod implements ModInitializer {
	//The crook_effective tag, used for blocks which should have its drops multiplied by crooks.
	public static final Dict<Block, Integer> CROOK_EFFECTIVE = DictManager.DATA_PACK.registerBlockDict(new Identifier("crookedcrooks", "crook_effective"), Integer.class);
	//The crooks tag, used to list all the crooks and to apply the multiplier.
	public static final Tag<Item> CROOKS = TagRegistry.item(new Identifier("crookedcrooks", "crooks"));
	
	//The crook items, which will be registered.
	//Vanilla Crooks
	public static final Item WOODEN_CROOK_ITEM = new CrookItem(CrookMaterials.WOOD, 0F, -3F, 0.2F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item STONE_CROOK_ITEM = new CrookItem(CrookMaterials.STONE, 0F, -3F, 0.25F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item IRON_CROOK_ITEM = new CrookItem(CrookMaterials.IRON, 0F, -3F, 0.5F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item DIAMOND_CROOK_ITEM = new CrookItem(CrookMaterials.DIAMOND, 0F, -3F, 0.625F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item GOLDEN_CROOK_ITEM = new CrookItem(CrookMaterials.GOLD, 0F, -3F, 0.2F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item NETHERITE_CROOK_ITEM = new CrookItem(CrookMaterials.NETHERITE, 0F, -3F, 0.75F, new Item.Settings().group(ItemGroup.TOOLS));
	//Tech Reborn Crooks
	public static final Item TECH_REBORN_BRONZE_CROOK_ITEM = new CrookItem(CrookMaterials.TECH_REBORN_BRONZE, 0F, -1F, 0.55F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item RUBY_CROOK_ITEM = new CrookItem(CrookMaterials.RUBY, 0F, -3F, 0.6F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item SAPPHIRE_CROOK_ITEM = new CrookItem(CrookMaterials.SAPPHIRE, 0F, -3F, 0.625F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item PERIDOT_CROOK_ITEM = new CrookItem(CrookMaterials.PERIDOT, 0F, -3F, 0.575F, new Item.Settings().group(ItemGroup.TOOLS));
	//Adabranium Crooks
	public static final Item VIBRANIUM_CROOK_ITEM = new CrookItem(CrookMaterials.VIBRANIUM, 0F, -3F, 0.75F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item ADAMANTIUM_CROOK_ITEM = new CrookItem(CrookMaterials.ADAMANTIUM, 0F, -3F, 0.85F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item NETHER_CROOK_ITEM = new CrookItem(CrookMaterials.NETHER, 0F, -3F, 0.5F, new Item.Settings().group(ItemGroup.TOOLS));
	//Astromine Crooks
	public static final Item METITE_CROOK_ITEM = new CrookItem(CrookMaterials.METITE, 0F, -3F, 0.3F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item ASTERITE_CROOK_ITEM = new CrookItem(CrookMaterials.ASTERITE, 0F, -3F, 0.7F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item STELLUM_CROOK_ITEM = new CrookItem(CrookMaterials.STELLUM, 0F, -3F, 0.75F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item GALAXIUM_CROOK_ITEM = new CrookItem(CrookMaterials.GALAXIUM, 0F, -3F, 0.8F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item UNIVITE_CROOK_ITEM = new CrookItem(CrookMaterials.UNIVITE, 0F, -3F, 0.85F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item LUNUM_CROOK_ITEM = new CrookItem(CrookMaterials.LUNUM, 0F, -3F, 0.65F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item METEORIC_STEEL_CROOK_ITEM = new CrookItem(CrookMaterials.METEORIC_STEEL, 0F, -3F, 0.625F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item COPPER_CROOK_ITEM = new CrookItem(CrookMaterials.COPPER, 0F, -3F, 0.25F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item TIN_CROOK_ITEM = new CrookItem(CrookMaterials.TIN, 0F, -3F, 0.275F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item SILVER_CROOK_ITEM = new CrookItem(CrookMaterials.SILVER, 0F, -3F, 0.5F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item LEAD_CROOK_ITEM = new CrookItem(CrookMaterials.LEAD, 0F, -3F, 0.45F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item ASTROMINE_BRONZE_CROOK_ITEM = new CrookItem(CrookMaterials.ASTROMINE_BRONZE, 0F, -3F, 0.55F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item STEEL_CROOK_ITEM = new CrookItem(CrookMaterials.STEEL, 0F, -3F, 0.6F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item ELECTRUM_CROOK_ITEM = new CrookItem(CrookMaterials.ELECTRUM, 0F, -3F, 0.5F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item ROSE_GOLD_CROOK_ITEM = new CrookItem(CrookMaterials.ROSE_GOLD, 0F, -3F, 0.2F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item STERLING_SILVER_CROOK_ITEM = new CrookItem(CrookMaterials.STERLING_SILVER, 0F, -3F, 0.55F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item FOOLS_GOLD_CROOK_ITEM = new CrookItem(CrookMaterials.FOOLS_GOLD, 0F, -3F, 0.25F, new Item.Settings().group(ItemGroup.TOOLS));
	//Applied Energistics 2 Crooks
	public static final Item CERTUS_QUARTZ_CROOK_ITEM = new CrookItem(CrookMaterials.IRON, 0F, -3F, 0.5F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item NETHER_QUARTZ_CROOK_ITEM = new CrookItem(CrookMaterials.NETHER_QUARTZ, 0F, -3F, 0.5F, new Item.Settings().group(ItemGroup.TOOLS));

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

		//Choose between Astromine and Tech Reborn's bronze stats.
		if (fabricLoader.isModLoaded("astromine")) {
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "bronze_crook"), ASTROMINE_BRONZE_CROOK_ITEM);
		} else if (fabricLoader.isModLoaded("techreborn")) {
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "bronze_crook"), TECH_REBORN_BRONZE_CROOK_ITEM);
		}

		if (fabricLoader.isModLoaded("techreborn")) {
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "ruby_crook"), RUBY_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "sapphire_crook"), SAPPHIRE_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "peridot_crook"), PERIDOT_CROOK_ITEM);
		}
		if (fabricLoader.isModLoaded("adabraniummod")) {
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "vibranium_crook"), VIBRANIUM_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "adamantium_crook"), ADAMANTIUM_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "nether_crook"), NETHER_CROOK_ITEM);
		}
		if (fabricLoader.isModLoaded("astromine-discoveries")) {
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "metite_crook"), METITE_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "asterite_crook"), ASTERITE_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "stellum_crook"), STELLUM_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "galaxium_crook"), GALAXIUM_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "univite_crook"), UNIVITE_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "lunum_crook"), LUNUM_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "meteoric_steel_crook"), METEORIC_STEEL_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "copper_crook"), COPPER_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "tin_crook"), TIN_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "silver_crook"), SILVER_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "lead_crook"), LEAD_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "steel_crook"), STEEL_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "electrum_crook"), ELECTRUM_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "rose_gold_crook"), ROSE_GOLD_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "sterling_silver_crook"), STERLING_SILVER_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "fools_gold_crook"), FOOLS_GOLD_CROOK_ITEM);
		}
		if (fabricLoader.isModLoaded("appliedenergistics2")) {
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "certus_quartz_crook"), CERTUS_QUARTZ_CROOK_ITEM);
			Registry.register(Registry.ITEM, new Identifier("crookedcrooks", "nether_quartz_crook"), NETHER_QUARTZ_CROOK_ITEM);
		}

		//TODO - Move this to another class
		PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, entity) -> {
			if (player.inventory.getMainHandStack().getItem().isIn(CrookedCrooksMod.CROOKS)) {
				if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, player.inventory.getMainHandStack()) == 0) {
					if (CrookedCrooksMod.CROOK_EFFECTIVE.contains(state.getBlock())) {
						int multiplier = CrookedCrooksMod.CROOK_EFFECTIVE.get(state.getBlock()).intValue() - 1;
						if (multiplier >= 0) {
							for (int i = 0; i < multiplier; i++) {
								Block.dropStacks(state, world, pos, entity, player, player.inventory.getMainHandStack());	
							}
						}
					}
				}
			}
		});

		//Make Wooden Crooks usable as fuel. More mods should have this.
		FuelRegistry.INSTANCE.add(WOODEN_CROOK_ITEM, 200);
	}
}
