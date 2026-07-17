package io.github.ennuil.crooked_crooks.data;

import net.minecraft.world.item.ToolMaterial;

public class CrookedToolMaterials {
	public static final ToolMaterial BONE = new ToolMaterial(
		ToolMaterial.STONE.incorrectBlocksForDrops(),
		ToolMaterial.STONE.durability(),
		ToolMaterial.STONE.speed(),
		ToolMaterial.STONE.attackDamageBonus(),
		ToolMaterial.STONE.enchantmentValue(),
		CrookedTags.Items.BONE_TOOL_MATERIALS
	);
}
