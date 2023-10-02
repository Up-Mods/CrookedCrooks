package io.github.ennuil.crooked_crooks.items;

import io.github.ennuil.crooked_crooks.CrookedCrooksMod;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class CrookTags {

	// The crooks tag, used to list all the crooks and to apply the multiplier
	public static final TagKey<Item> CROOKS = tag("crooks");

	public static class Common {

		public static final TagKey<Item> RUBIES = tag("rubies");
		public static final TagKey<Item> SAPPHIRES = tag("sapphires");
		public static final TagKey<Item> PERIDOTS = tag("peridots");
		public static final TagKey<Item> CERTUS_QUARTZ = tag("certus_quartz");
		public static final TagKey<Item> QUARTZ = tag("quartz");
		public static final TagKey<Item> FLUIX = tag("fluix");
		public static final TagKey<Item> TIN_INGOTS = tag("tin_ingots");
		public static final TagKey<Item> COPPER_INGOTS = tag("copper_ingots");
		public static final TagKey<Item> BRONZE_INGOTS = tag("bronze_ingots");
		public static final TagKey<Item> STEEL_INGOTS = tag("steel_ingots");
		public static final TagKey<Item> LEAD_INGOTS = tag("lead_ingots");
		public static final TagKey<Item> NETHERITE_INGOTS = tag("netherite_ingots");

		private static TagKey<Item> tag(String path) {
			return TagKey.of(RegistryKeys.ITEM, new Identifier("c", path));
		}
	}

	private static TagKey<Item> tag(String path) {
		return TagKey.of(RegistryKeys.ITEM, new Identifier(CrookedCrooksMod.MODID, path));
	}
}
