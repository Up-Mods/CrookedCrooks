package io.github.ennuil.crooked_crooks.data;

import io.github.ennuil.crooked_crooks.CrookedCrooks;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class CrookedTags {

	public static class Blocks {

		public static final TagKey<Block> MINEABLE_WITH_CROOK = TagKey.create(Registries.BLOCK, CrookedCrooks.id("mineable_with_crook"));
		public static final TagKey<Block> MULTIPLE_DROOPS_WHEN_CROOKED = TagKey.create(Registries.BLOCK, CrookedCrooks.id("multiple_drops_when_crooked"));
	}

	public static class Items {

		public static final TagKey<Item> CROOKS = TagKey.create(Registries.ITEM, CrookedCrooks.id("crooks"));
		public static final TagKey<Item> BONE_TOOL_MATERIALS = TagKey.create(Registries.ITEM, CrookedCrooks.id("bone_tool_materials"));
	}
}
