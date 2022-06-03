package io.github.ennuil.crooked_crooks;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.registry.api.event.RegistryEvents;
import org.quiltmc.qsl.registry.attachment.api.RegistryEntryAttachment;
import org.quiltmc.qsl.resource.loader.api.ResourceLoader;
import org.quiltmc.qsl.resource.loader.api.ResourcePackActivationType;

import io.github.ennuil.crooked_crooks.enchantments.ThornsCurseEnchantment;
import io.github.ennuil.crooked_crooks.events.MultiplyDropsEvent;
import io.github.ennuil.crooked_crooks.items.CrookItem;
import io.github.ennuil.crooked_crooks.items.CrookMaterials;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tag.TagKey;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CrookedCrooksMod implements ModInitializer {
	// The crook_effective dict, used for blocks which should have its drops multiplied by crooks
	public static final RegistryEntryAttachment<Block, Integer> CROOK_EFFECTIVE =
		RegistryEntryAttachment.intBuilder(Registry.BLOCK, new Identifier("crooked_crooks", "crook_effective")).build();
	// The mob_weight dict, used to override the automatically calculated weights for each mob.
	public static final RegistryEntryAttachment<EntityType<?>, Double> ENTITY_WEIGHT =
		RegistryEntryAttachment.doubleBuilder(Registry.ENTITY_TYPE, new Identifier("crooked_crooks", "entity_weight")).build();
	// The crooks tag, used to list all the crooks and to apply the multiplier
	public static final TagKey<Item> CROOKS = TagKey.of(Registry.ITEM_KEY, new Identifier("crooked_crooks", "crooks"));

	// The crook items, which are going to be registered
	// Only Vanilla crooks are guaranteed to be registered every single time, so we have constants for them!
	public static final Item WOODEN_CROOK_ITEM = new CrookItem(CrookMaterials.WOOD, 0F, -3F, 0.4F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item STONE_CROOK_ITEM = new CrookItem(CrookMaterials.STONE, 0F, -3F, 0.5F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item IRON_CROOK_ITEM = new CrookItem(CrookMaterials.IRON, 0F, -3F, 0.8F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item DIAMOND_CROOK_ITEM = new CrookItem(CrookMaterials.DIAMOND, 0F, -3F, 1.0F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item GOLDEN_CROOK_ITEM = new CrookItem(CrookMaterials.GOLD, 0F, -3F, 0.4F, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item NETHERITE_CROOK_ITEM = new CrookItem(CrookMaterials.NETHERITE, 0F, -3F, 1.2F, new Item.Settings().group(ItemGroup.TOOLS).fireproof());

	// The modded items to be used as a point of reference by Crooked Crooks
	private static final Identifier ROSE_GOLD_HOE_ITEM = new Identifier("additionaladditions", "rose_gold_hoe");
	private static final Identifier GILDED_NETHERITE_HOE_ITEM = new Identifier("additionaladditions", "gilded_netherite_hoe");

	// The crook enchantments
	public static final Enchantment THORNS_CURSE_ENCHANTMENT = new ThornsCurseEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND);

	@Override
	public void onInitialize(ModContainer mod) {
		// Register all the crooks
		Registry.register(Registry.ITEM, new Identifier("crooked_crooks", "wooden_crook"), WOODEN_CROOK_ITEM);
		Registry.register(Registry.ITEM, new Identifier("crooked_crooks", "stone_crook"), STONE_CROOK_ITEM);
		Registry.register(Registry.ITEM, new Identifier("crooked_crooks", "iron_crook"), IRON_CROOK_ITEM);
		Registry.register(Registry.ITEM, new Identifier("crooked_crooks", "diamond_crook"), DIAMOND_CROOK_ITEM);
		Registry.register(Registry.ITEM, new Identifier("crooked_crooks", "golden_crook"), GOLDEN_CROOK_ITEM);
		Registry.register(Registry.ITEM, new Identifier("crooked_crooks", "netherite_crook"), NETHERITE_CROOK_ITEM);

		if (QuiltLoader.isModLoaded("techreborn")) {
			Registry.register(
				Registry.ITEM, new Identifier("crooked_crooks", "ruby_crook"),
				new CrookItem(CrookMaterials.RUBY, 0F, -3F, 0.9F, new Item.Settings().group(ItemGroup.TOOLS)));
			Registry.register(
				Registry.ITEM, new Identifier("crooked_crooks", "sapphire_crook"),
				new CrookItem(CrookMaterials.SAPPHIRE, 0F, -3F, 1.0F, new Item.Settings().group(ItemGroup.TOOLS)));
			Registry.register(
				Registry.ITEM, new Identifier("crooked_crooks", "peridot_crook"),
				new CrookItem(CrookMaterials.PERIDOT, 0F, -3F, 0.875F, new Item.Settings().group(ItemGroup.TOOLS)));
			Registry.register(
				Registry.ITEM, new Identifier("crooked_crooks", "tr_bronze_crook"),
				new CrookItem(CrookMaterials.TECH_REBORN_BRONZE, 0F, -3F, 0.85F, new Item.Settings().group(ItemGroup.TOOLS)));

			if (QuiltLoader.isModLoaded("indrev")) {
				ResourceLoader.registerBuiltinResourcePack(
					new Identifier("crooked_crooks", "use_tr_bronze_crooks"),
					mod,
					ResourcePackActivationType.NORMAL,
					new TranslatableText("resource_pack.use_tr_bronze_crooks"));
			}
		}

		if (QuiltLoader.isModLoaded("ae2")) {
			Registry.register(
				Registry.ITEM, new Identifier("crooked_crooks", "certus_quartz_crook"),
				new CrookItem(CrookMaterials.IRON, 0F, -3F, 0.8F, new Item.Settings().group(ItemGroup.TOOLS)));
			Registry.register(
				Registry.ITEM, new Identifier("crooked_crooks", "nether_quartz_crook"),
				new CrookItem(CrookMaterials.NETHER_QUARTZ, 0F, -3F, 0.8F, new Item.Settings().group(ItemGroup.TOOLS)));
		}

		if (QuiltLoader.isModLoaded("indrev")) {
			Registry.register(
				Registry.ITEM, new Identifier("crooked_crooks", "tin_crook"),
				new CrookItem(CrookMaterials.TIN, 0F, -3F, 0.5F, new Item.Settings().group(ItemGroup.TOOLS)));
			Registry.register(
				Registry.ITEM, new Identifier("crooked_crooks", "copper_crook"),
				new CrookItem(CrookMaterials.COPPER, 0F, -3F, 0.8F, new Item.Settings().group(ItemGroup.TOOLS)));
			Registry.register(
				Registry.ITEM, new Identifier("crooked_crooks", "steel_crook"),
				new CrookItem(CrookMaterials.STEEL, 0F, -3F, 0.9F, new Item.Settings().group(ItemGroup.TOOLS)));
			Registry.register(
				Registry.ITEM, new Identifier("crooked_crooks", "lead_crook"),
				new CrookItem(CrookMaterials.LEAD, 0F, -3F, 0.75F, new Item.Settings().group(ItemGroup.TOOLS)));
			Registry.register(
				Registry.ITEM, new Identifier("crooked_crooks", "ir_bronze_crook"),
				new CrookItem(CrookMaterials.INDREV_BRONZE, 0F, -3F, 0.85F, new Item.Settings().group(ItemGroup.TOOLS)));
			Registry.register(
				Registry.ITEM, new Identifier("crooked_crooks", "silver_crook"),
				new CrookItem(CrookMaterials.SILVER, 0F, -3F, 0.8F, new Item.Settings().group(ItemGroup.TOOLS)));
		}

		/*
		if (QuiltLoader.isModLoaded("betterend")) {
			Registry.register(
				Registry.ITEM, new Identifier("crooked_crooks", "thallasium_crook"),
				new CrookItem(CrookMaterials.THALLASIUM, 0F, -3F, 0.8F, new Item.Settings().group(ItemGroup.TOOLS)));
			Registry.register(
				Registry.ITEM, new Identifier("crooked_crooks", "terminite_crook"),
				new CrookItem(CrookMaterials.TERMINITE, 0F, -3F, 1.0F, new Item.Settings().group(ItemGroup.TOOLS)));
			Registry.register(
				Registry.ITEM, new Identifier("crooked_crooks", "aeternium_crook"),
				new CrookItem(CrookMaterials.AETERNIUM, 0F, -3F, 1.3F, new Item.Settings().group(ItemGroup.TOOLS)));
		}
		*/

		// Additional Additions has config options for disabling certain items
		// Currently, the tools aren't affected by them, but future-proofing is a good idea
		if (QuiltLoader.isModLoaded("additionaladditions")) {
			RegistryEvents.getEntryAddEvent(Registry.ITEM).register(ctx -> {
				if (ctx.id().equals(ROSE_GOLD_HOE_ITEM)) {
					ctx.register(new Identifier("crooked_crooks", "rose_gold_crook"),
						new CrookItem(CrookMaterials.ROSE_GOLD, 0F, -3F, 1.3F, new Item.Settings().group(ItemGroup.TOOLS)));
				} else if (ctx.id().equals(GILDED_NETHERITE_HOE_ITEM)) {
					ctx.register(new Identifier("crooked_crooks", "gilded_netherite_crook"),
						new CrookItem(CrookMaterials.GILDED_NETHERITE, 0F, -3F, 1.3F, new Item.Settings().group(ItemGroup.TOOLS).fireproof()));
				}
			});
		}

		// Register enchantments
		Registry.register(Registry.ENCHANTMENT, "crooked_crooks:thorns_curse", THORNS_CURSE_ENCHANTMENT);

		// Register the drop-multiplying event
		MultiplyDropsEvent.registerEvent();

		// TODO - Once Quilt Item API releases, add to its attachment!
		// Make Wooden Crooks usable as fuel. More mods should have this
		FuelRegistry.INSTANCE.add(WOODEN_CROOK_ITEM, 200);
	}
}
