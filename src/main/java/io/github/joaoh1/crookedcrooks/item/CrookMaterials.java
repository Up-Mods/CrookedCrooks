package io.github.joaoh1.crookedcrooks.item;

import java.util.function.Supplier;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.Lazy;
import net.minecraft.util.registry.Registry;

public enum CrookMaterials implements ToolMaterial {
	//Vanilla Materials
	WOOD(ToolMaterials.WOOD),
	STONE(ToolMaterials.STONE),
	IRON(ToolMaterials.IRON),
	DIAMOND(ToolMaterials.DIAMOND),
	GOLD(ToolMaterials.GOLD),
	NETHERITE(ToolMaterials.NETHERITE),
	//Tech Reborn Materials
	TECH_REBORN_BRONZE(2, 375, 7.0F, 2.25F, 6, () -> {
		return Ingredient.fromTag(TagRegistry.item(new Identifier("c:bronze_ingots")));
	}),
	RUBY(2, 750, 6.0F, 1.5F, 10, () -> {
		return Ingredient.fromTag(TagRegistry.item(new Identifier("c:rubies")));
	}),
	SAPPHIRE(3, 1000, 7.0F, 1.5F, 12, () -> {
		return Ingredient.fromTag(TagRegistry.item(new Identifier("c:sapphires")));
	}),
	PERIDOT(2, 750, 7.0F, 1.5F, 12, () -> {
		return Ingredient.fromTag(TagRegistry.item(new Identifier("c:peridot_gems")));
	}),
	//Adabranium Materials
	VIBRANIUM(3, 2990, 20F, 10.0F, 12, () -> {
		return Ingredient.ofItems(Registry.ITEM.get(new Identifier("adabraniummod:vibranium_ingot")));
	}),
	ADAMANTIUM(4, 4280, 35.0F, 16.0F, 10, () -> {
		return Ingredient.ofItems(Registry.ITEM.get(new Identifier("adabraniummod:adamantium_ingot")));
	}),
	NETHER(2, 381, 8.0F, 2.0F, 15, () -> {
		return Ingredient.ofItems(Items.NETHER_BRICK);
	}),
	//Astromine Materials
	METITE(1, 853, 13.0F, 4.0F, 5, () -> {
		return Ingredient.fromTag(TagRegistry.item(new Identifier("c:metite_ingots")));
	}),
	ASTERITE(5, 2015, 10.0F, 5.0F, 20, () -> {
		return Ingredient.fromTag(TagRegistry.item(new Identifier("c:asterites")));
	}),
	STELLUM(5, 2643, 8.0F, 6.0F, 15, () -> {
		return Ingredient.fromTag(TagRegistry.item(new Identifier("c:stellum_ingots")));
	}),
	GALAXIUM(6, 3072, 11.0F, 5.0F, 18, () -> {
		return Ingredient.fromTag(TagRegistry.item(new Identifier("c:galaxiums")));
	}),
	UNIVITE(7, 3918, 12.0F, 6.0F, 22, () -> {
		return Ingredient.fromTag(TagRegistry.item(new Identifier("c:univite_ingots")));
	}),
	LUNUM(4, 1382, 7.0F, 4.5F, 18, () -> {
		return Ingredient.fromTag(TagRegistry.item(new Identifier("c:lunum_ingots")));
	}),
	METEORIC_STEEL(3, 949, 10.5F, 3.5F, 10, () -> {
		return Ingredient.fromTag(TagRegistry.item(new Identifier("c:meteoric_steel_ingots")));
	}),
	COPPER(1, 200, 4.0F, 1.5F, 10, () -> {
		return Ingredient.fromTag(TagRegistry.item(new Identifier("c:copper_ingots")));
	}),
	TIN(1, 200, 5F, 1.0F, 10, () -> {
		return Ingredient.fromTag(TagRegistry.item(new Identifier("c:tin_ingots")));
	}),
	SILVER(2, 462, 6.5F, 2.0F, 20, () -> {
		return Ingredient.fromTag(TagRegistry.item(new Identifier("c:silver_ingots")));
	}),
	LEAD(2, 496, 4.5F, 1.5F, 5, () -> {
		return Ingredient.fromTag(TagRegistry.item(new Identifier("c:lead_ingots")));
	}),
	ASTROMINE_BRONZE(2, 539, 7.0F, 2.5F, 18, () -> {
		return Ingredient.fromTag(TagRegistry.item(new Identifier("c:bronze_ingots")));
	}),
	STEEL(3, 1043, 7.5F, 3.0F, 16, () -> {
		return Ingredient.fromTag(TagRegistry.item(new Identifier("c:steel_ingots")));
	}),
	ELECTRUM(2, 185, 11.0F, 1.0F, 21, () -> {
		return Ingredient.fromTag(TagRegistry.item(new Identifier("c:electrum_ingots")));
	}),
	ROSE_GOLD(1, 64, 10.0F, 0.5F, 24, () -> {
		return Ingredient.fromTag(TagRegistry.item(new Identifier("c:rose_gold_ingots")));
	}),
	STERLING_SILVER(2, 697, 7.0F, 2.5F, 20, () -> {
		return Ingredient.fromTag(TagRegistry.item(new Identifier("c:sterling_silver_ingots")));
	}),
	FOOLS_GOLD(2, 250, 6.5F, 2.0F, 16, () -> {
		return Ingredient.fromTag(TagRegistry.item(new Identifier("c:fools_gold_ingots")));
	}),
	//Applied Energistics 2 Materials
	CERTUS_QUARTZ(ToolMaterials.IRON.getMiningLevel(), ToolMaterials.IRON.getDurability(), ToolMaterials.IRON.getMiningLevel(), ToolMaterials.IRON.getAttackDamage(), ToolMaterials.IRON.getEnchantability(), () -> {
		return Ingredient.fromTag(TagRegistry.item(new Identifier("appliedenergistics2:crystals/certus_quartz")));
	}),
	NETHER_QUARTZ(ToolMaterials.IRON.getMiningLevel(), ToolMaterials.IRON.getDurability(), ToolMaterials.IRON.getMiningLevel(), ToolMaterials.IRON.getAttackDamage(), ToolMaterials.IRON.getEnchantability(), () -> {
		return Ingredient.fromTag(TagRegistry.item(new Identifier("c:quartz")));
	});

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
		this.repairIngredient = new Lazy<Ingredient>(() -> {
			return material.getRepairIngredient();
		});
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