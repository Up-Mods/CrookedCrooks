package io.github.ennuil.crookedcrooks.items;

import java.util.function.Supplier;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.Lazy;

@SuppressWarnings("deprecation")
public enum CrookMaterials implements ToolMaterial {
	// Vanilla Materials
	WOOD(ToolMaterials.WOOD),
	STONE(ToolMaterials.STONE),
	IRON(ToolMaterials.IRON),
	DIAMOND(ToolMaterials.DIAMOND),
	GOLD(ToolMaterials.GOLD),
	NETHERITE(ToolMaterials.NETHERITE),
	// Tech Reborn Materials
	TECH_REBORN_BRONZE(2, 375, 7.0F, 2.25F, 6, () -> Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("c:bronze_ingots")))),
	RUBY(2, 750, 6.0F, 1.5F, 10, () -> Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("c:rubies")))),
	SAPPHIRE(3, 1000, 7.0F, 1.5F, 12, () -> Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("c:sapphires")))),
	PERIDOT(2, 750, 7.0F, 1.5F, 12, () -> Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("c:peridot_gems")))),
	// Applied Energistics 2 Materials
	CERTUS_QUARTZ(ToolMaterials.IRON, () -> Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("c:certus_quartz")))),
	NETHER_QUARTZ(ToolMaterials.IRON, () -> Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("c:quartz")))),
	// Industrial Revolution Materials
	TIN(1, 200, 4.0F, 1.0F, 14, () -> Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("c:tin_ingots")))),
	COPPER(2, 300, 4.5F, 1.0F, 14, () -> Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("c:copper_ingots")))),
	STEEL(3, 600, 4.5F, 2.0F, 14, () -> Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("c:steel_ingots")))),
	LEAD(2, 900, 3.0F, 2.0F, 8, () -> Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("c:lead_ingots")))),
	INDREV_BRONZE(2, 500, 3.5F, 2.5F, 12, () -> Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("c:bronze_ingots")))),
	SILVER(2, 500, 5.0F, 1.0F, 24, () -> Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("c:lead_ingots")))),
	/*
	// Better End Materials
	THALLASIUM(MiningLevels.IRON, 320, 7.0F, 1.5F, 12, () -> Ingredient.ofItems(Registry.ITEM.get(new Identifier("betterend:thallasium_ingot")))),
	TERMINITE(MiningLevels.DIAMOND, 1230, 8.5F, 3.0F, 14, () -> Ingredient.ofItems(Registry.ITEM.get(new Identifier("betterend:terminite_ingot")))),
	// Why must you cause pain and suffering for me ;-;
	AETERNIUM(5, 2196, 10.0F, 4.5F, 18, () -> Ingredient.ofItems(Registry.ITEM.get(new Identifier("betterend:aeternium_ingot")))),
	*/
	// Additional Additions Materials
	ROSE_GOLD(2, 900, 9.0F, 3.0F, 17, () -> Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("c:copper_ingots")))),
	GILDED_NETHERITE(4, 2000, 9.0F, 4.0F, 24, () -> Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("c:netherite_ingots"))));

	private final int miningLevel;
	private final int itemDurability;
	private final float miningSpeed;
	private final float attackDamage;
	private final int enchantability;
	private final Lazy<Ingredient> repairIngredient;

	private CrookMaterials(ToolMaterial material) {
		this.miningLevel = material.getMiningLevel();
		this.itemDurability = material.getDurability();
		this.miningSpeed = material.getMiningSpeedMultiplier();
		this.attackDamage = material.getAttackDamage();
		this.enchantability = material.getEnchantability();
		this.repairIngredient = new Lazy<Ingredient>(() -> material.getRepairIngredient());
	}

	private CrookMaterials(ToolMaterial material, Supplier<Ingredient> repairIngredient) {
		this.miningLevel = material.getMiningLevel();
		this.itemDurability = material.getDurability();
		this.miningSpeed = material.getMiningSpeedMultiplier();
		this.attackDamage = material.getAttackDamage();
		this.enchantability = material.getEnchantability();
		this.repairIngredient = new Lazy<Ingredient>(repairIngredient);
	}

	private CrookMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
		this.miningLevel = miningLevel;
		this.itemDurability = itemDurability;
		this.miningSpeed = miningSpeed;
		this.attackDamage = attackDamage;
		this.enchantability = enchantability;
		this.repairIngredient = new Lazy<Ingredient>(repairIngredient);
	}

	@Override
	public int getMiningLevel() {
		return this.miningLevel;
	}

	@Override
	public int getDurability() {
		return this.itemDurability;
	}

	@Override
	public float getMiningSpeedMultiplier() {
		return this.miningSpeed;
	}

	@Override
	public float getAttackDamage() {
		return this.attackDamage;
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return this.repairIngredient.get();
	}
}