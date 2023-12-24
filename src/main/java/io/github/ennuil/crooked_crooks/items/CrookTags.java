package io.github.ennuil.crooked_crooks.items;

import io.github.ennuil.crooked_crooks.CrookedCrooksMod;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class CrookTags {

	// The crooks tag, used to list all the crooks and to apply the multiplier
	public static final TagKey<Item> CROOKS = tag("crooks");
	public static final TagKey<Item> QUARTZ_CROOKS = tag("quartz_crooks");

	public static class Common {

		public static final TagKey<Item> RUBIES = tag("rubies");
		public static final TagKey<Item> SAPPHIRES = tag("sapphires");
		public static final TagKey<Item> PERIDOTS = tag("peridots");
		public static final TagKey<Item> DIAMONDS = ConventionalItemTags.DIAMONDS;
		public static final TagKey<Item> CERTUS_QUARTZ = tag("certus_quartz");
		public static final TagKey<Item> QUARTZ = ConventionalItemTags.QUARTZ;
		public static final TagKey<Item> FLUIX = tag("fluix");
		public static final TagKey<Item> IRON_NUGGETS = tag("iron_nuggets");
		public static final TagKey<Item> GOLD_NUGGETS = tag("gold_nuggets");
		public static final TagKey<Item> COPPER_INGOTS = ConventionalItemTags.COPPER_INGOTS;
		public static final TagKey<Item> BRONZE_INGOTS = tag("bronze_ingots");
		public static final TagKey<Item> BRONZE_NUGGETS = tag("bronze_nuggets");
		public static final TagKey<Item> NETHERITE_INGOTS = ConventionalItemTags.NETHERITE_INGOTS;

		private static TagKey<Item> tag(String path) {
			return TagKey.of(RegistryKeys.ITEM, new Identifier("c", path));
		}
	}

	private static TagKey<Item> tag(String path) {
		return TagKey.of(RegistryKeys.ITEM, new Identifier(CrookedCrooksMod.MOD_ID, path));
	}
}
