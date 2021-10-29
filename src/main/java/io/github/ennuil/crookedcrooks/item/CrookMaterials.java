package io.github.ennuil.crookedcrooks.item;

import java.util.function.Supplier;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.Lazy;

public enum CrookMaterials implements ToolMaterial {
	// Vanilla Materials
	WOOD(ToolMaterials.WOOD),
	STONE(ToolMaterials.STONE),
	IRON(ToolMaterials.IRON),
	DIAMOND(ToolMaterials.DIAMOND),
	GOLD(ToolMaterials.GOLD),
	NETHERITE(ToolMaterials.NETHERITE),
	// Tech Reborn Materials
	TECH_REBORN_BRONZE(MiningLevels.IRON, 375, 7.0F, 2.25F, 6, () -> Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("c:bronze_ingots")))),
	RUBY(MiningLevels.IRON, 750, 6.0F, 1.5F, 10, () -> Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("c:rubies")))),
	SAPPHIRE(MiningLevels.DIAMOND, 1000, 7.0F, 1.5F, 12, () -> Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("c:sapphires")))),
	PERIDOT(MiningLevels.IRON, 750, 7.0F, 1.5F, 12, () -> Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("c:peridot_gems")))),
	// Applied Energistics 2 Materials
	CERTUS_QUARTZ(ToolMaterials.IRON, () -> Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("appliedenergistics2:crystals/certus_quartz")))),
	NETHER_QUARTZ(ToolMaterials.IRON, () -> Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("c:quartz")))),
	// Industrial Revolution Materials
	TIN(MiningLevels.STONE, 200, 4.0F, 1.0F, 14, () -> Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("c:tin_ingots")))),
	COPPER(MiningLevels.IRON, 300, 4.5F, 1.0F, 14, () -> Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("c:copper_ingots")))),
	STEEL(MiningLevels.DIAMOND, 600, 4.5F, 2.0F, 14, () -> Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("c:steel_ingots")))),
	LEAD(MiningLevels.IRON, 900, 3.0F, 2.0F, 8, () -> Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("c:lead_ingots")))),
	INDREV_BRONZE(MiningLevels.IRON, 500, 3.5F, 2.5F, 12, () -> Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("c:bronze_ingots")))),
	SILVER(MiningLevels.IRON, 500, 5.0F, 1.0F, 24, () -> Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("c:lead_ingots"))));

	private final int miningLevel;
	private final int itemDurability;
	private final float miningSpeed;
	private final float attackDamage;
	private final int enchantability;
	private final Lazy<Ingredient> repairIngredient;

	private CrookMaterials(ToolMaterial material) {
		this.miningLevel = material.getMiningLevel();
		this.itemDurability = material.getDurability();
		this.miningSpeed = material.getMiningLevel();
		this.attackDamage = material.getAttackDamage();
		this.enchantability = material.getEnchantability();
		this.repairIngredient = new Lazy<Ingredient>(() -> material.getRepairIngredient());
	}

	private CrookMaterials(ToolMaterial material, Supplier<Ingredient> repairIngredient) {
		this.miningLevel = material.getMiningLevel();
		this.itemDurability = material.getDurability();
		this.miningSpeed = material.getMiningLevel();
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