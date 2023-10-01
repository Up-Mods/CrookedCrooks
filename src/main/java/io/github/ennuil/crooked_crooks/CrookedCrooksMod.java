package io.github.ennuil.crooked_crooks;

import io.github.ennuil.crooked_crooks.enchantments.ThornsCurseEnchantment;
import io.github.ennuil.crooked_crooks.events.MultiplyDropsEvent;
import io.github.ennuil.crooked_crooks.items.CrookItem;
import io.github.ennuil.crooked_crooks.items.CrookMaterials;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.registry.api.event.RegistryMonitor;
import org.quiltmc.qsl.registry.attachment.api.RegistryEntryAttachment;
import org.quiltmc.qsl.resource.loader.api.ResourceLoader;
import org.quiltmc.qsl.resource.loader.api.ResourcePackActivationType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class CrookedCrooksMod implements ModInitializer {

	public static final String MODID = "crooked_crooks";

	// The crook_effective dict, used for blocks which should have its drops multiplied by crooks
	public static final RegistryEntryAttachment<Block, Integer> CROOK_EFFECTIVE =
		RegistryEntryAttachment.intBuilder(Registries.BLOCK, new Identifier(MODID, "crook_effective")).build();
	// The mob_weight dict, used to override the automatically calculated weights for each mob.
	public static final RegistryEntryAttachment<EntityType<?>, Double> ENTITY_WEIGHT =
		RegistryEntryAttachment.doubleBuilder(Registries.ENTITY_TYPE, new Identifier(MODID, "entity_weight")).build();
	// The crooks tag, used to list all the crooks and to apply the multiplier
	public static final TagKey<Item> CROOKS = TagKey.of(RegistryKeys.ITEM, new Identifier(MODID, "crooks"));

	// The crook items, which are going to be registered
	// Only Vanilla crooks are guaranteed to be registered every single time, so we have constants for them!
	public static final Item WOODEN_CROOK_ITEM = new CrookItem(CrookMaterials.WOOD, 0F, -3F, 0.4F, new Item.Settings());
	public static final Item STONE_CROOK_ITEM = new CrookItem(CrookMaterials.STONE, 0F, -3F, 0.5F, new Item.Settings());
	public static final Item IRON_CROOK_ITEM = new CrookItem(CrookMaterials.IRON, 0F, -3F, 0.8F, new Item.Settings());
	public static final Item DIAMOND_CROOK_ITEM = new CrookItem(CrookMaterials.DIAMOND, 0F, -3F, 1.0F, new Item.Settings());
	public static final Item GOLDEN_CROOK_ITEM = new CrookItem(CrookMaterials.GOLD, 0F, -3F, 0.4F, new Item.Settings());
	public static final Item NETHERITE_CROOK_ITEM = new CrookItem(CrookMaterials.NETHERITE, 0F, -3F, 1.2F, new Item.Settings().fireproof());

	// The modded items to be used as a point of reference by Crooked Crooks
	private static final Identifier ROSE_GOLD_HOE_ITEM = new Identifier("additionaladditions", "rose_gold_hoe");
	private static final Identifier GILDED_NETHERITE_HOE_ITEM = new Identifier("additionaladditions", "gilded_netherite_hoe");

	// The crook enchantments
	public static final Enchantment THORNS_CURSE_ENCHANTMENT = new ThornsCurseEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND);

	@Override
	public void onInitialize(ModContainer mod) {
		List<ItemStack> ITEMS = new ArrayList<>();

		BiConsumer<String, Item> register = (id, item) -> {
			Registry.register(Registries.ITEM, new Identifier(MODID, id), item);
			ITEMS.add(item.getDefaultStack());
		};

		// Register all the crooks
		register.accept("wooden_crook", WOODEN_CROOK_ITEM);
		register.accept("stone_crook", STONE_CROOK_ITEM);
		register.accept("iron_crook", IRON_CROOK_ITEM);
		register.accept("diamond_crook", DIAMOND_CROOK_ITEM);
		register.accept("golden_crook", GOLDEN_CROOK_ITEM);
		register.accept("netherite_crook", NETHERITE_CROOK_ITEM);

		if (QuiltLoader.isModLoaded("techreborn")) {
			register.accept("ruby_crook", new CrookItem(CrookMaterials.RUBY, 0F, -3F, 0.9F, new Item.Settings()));
			register.accept("sapphire_crook", new CrookItem(CrookMaterials.SAPPHIRE, 0F, -3F, 1.0F, new Item.Settings()));
			register.accept("peridot_crook", new CrookItem(CrookMaterials.PERIDOT, 0F, -3F, 0.875F, new Item.Settings()));
			register.accept("tr_bronze_crook", new CrookItem(CrookMaterials.TECH_REBORN_BRONZE, 0F, -3F, 0.85F, new Item.Settings()));

			if (QuiltLoader.isModLoaded("indrev")) {
				ResourceLoader.registerBuiltinResourcePack(
					new Identifier(MODID, "use_tr_bronze_crooks"),
					mod,
					ResourcePackActivationType.NORMAL,
					Text.translatable("resource_pack.use_tr_bronze_crooks"));
			}
		}

		if (QuiltLoader.isModLoaded("ae2")) {
			register.accept("certus_quartz_crook", new CrookItem(CrookMaterials.IRON, 0F, -3F, 0.8F, new Item.Settings()));
			register.accept("nether_quartz_crook", new CrookItem(CrookMaterials.NETHER_QUARTZ, 0F, -3F, 0.8F, new Item.Settings()));
			register.accept("fluix_crook", new CrookItem(CrookMaterials.FLUIX, 0F, -3F, 0.96F, new Item.Settings()) {
				@Override
				public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
					tooltip.add(Text.translatable("gui.ae2.IntrinsicEnchant", Enchantments.FORTUNE.getName(1)));
				}

				@Override
				public boolean hasGlint(ItemStack stack) {
					return true;
				}
			});
		}

		if (QuiltLoader.isModLoaded("indrev")) {
			register.accept("tin_crook", new CrookItem(CrookMaterials.TIN, 0F, -3F, 0.5F, new Item.Settings()));
			register.accept("copper_crook", new CrookItem(CrookMaterials.COPPER, 0F, -3F, 0.8F, new Item.Settings()));
			register.accept("steel_crook", new CrookItem(CrookMaterials.STEEL, 0F, -3F, 0.9F, new Item.Settings()));
			register.accept("lead_crook", new CrookItem(CrookMaterials.LEAD, 0F, -3F, 0.75F, new Item.Settings()));
			register.accept("ir_bronze_crook", new CrookItem(CrookMaterials.INDREV_BRONZE, 0F, -3F, 0.85F, new Item.Settings()));
			register.accept("silver_crook", new CrookItem(CrookMaterials.SILVER, 0F, -3F, 0.8F, new Item.Settings()));
		}

		/*
		if (QuiltLoader.isModLoaded("betterend")) {
			register.accept("thallasium_crook", new CrookItem(CrookMaterials.THALLASIUM, 0F, -3F, 0.8F, new Item.Settings()));
			register.accept("terminite_crook", new CrookItem(CrookMaterials.TERMINITE, 0F, -3F, 1.0F, new Item.Settings()));
			register.accept("aeternium_crook", new CrookItem(CrookMaterials.AETERNIUM, 0F, -3F, 1.3F, new Item.Settings()));
		}
		*/

		// Additional Additions has config options for disabling certain items
		// Currently, the tools aren't affected by them, but future-proofing is a good idea
		if (QuiltLoader.isModLoaded("additionaladditions")) {
			RegistryMonitor.create(Registries.ITEM).forAll(ctx -> {
				if (ctx.id().equals(ROSE_GOLD_HOE_ITEM)) {
					Item item = new CrookItem(CrookMaterials.ROSE_GOLD, 0F, -3F, 1.3F, new Item.Settings());
					ctx.register(new Identifier(MODID, "rose_gold_crook"), item);
					ITEMS.add(item.getDefaultStack());
				} else if (ctx.id().equals(GILDED_NETHERITE_HOE_ITEM)) {
					Item item = new CrookItem(CrookMaterials.GILDED_NETHERITE, 0F, -3F, 1.3F, new Item.Settings().fireproof());
					ctx.register(new Identifier(MODID, "gilded_netherite_crook"), item);
					ITEMS.add(item.getDefaultStack());
				}
			});
		}

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS_AND_UTILITIES).register(entries -> entries.addBefore(Items.BUCKET, ITEMS));

		// Register enchantments
		Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "thorns_curse"), THORNS_CURSE_ENCHANTMENT);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> entries.addStack(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(THORNS_CURSE_ENCHANTMENT, THORNS_CURSE_ENCHANTMENT.getMaxLevel()))));

		// Register the drop-multiplying event
		MultiplyDropsEvent.registerEvent();
	}
}
