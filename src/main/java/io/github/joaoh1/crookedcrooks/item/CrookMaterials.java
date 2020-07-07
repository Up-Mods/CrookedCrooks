package io.github.joaoh1.crookedcrooks.item;

import java.util.function.Supplier;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.Lazy;
import net.minecraft.util.registry.Registry;

public enum CrookMaterials implements ToolMaterial {
	//Vanilla Materials.
	WOOD(ToolMaterials.WOOD),
	STONE(ToolMaterials.STONE),
	IRON(ToolMaterials.IRON),
	DIAMOND(ToolMaterials.DIAMOND),
	GOLD(ToolMaterials.GOLD),
	NETHERITE(ToolMaterials.NETHERITE),
	//Tech Reborn Materials.
	BRONZE(2, 375, 7.0F, 2.25F, 12, () -> {
		return Ingredient.ofItems(Registry.ITEM.get(new Identifier("techreborn", "bronze_ingot")));
	}),
	RUBY(2, 1651, 6.0F, 4.7F, 10, () -> {
		return Ingredient.ofItems(Registry.ITEM.get(new Identifier("techreborn", "ruby_gem")));
	}),
	SAPPHIRE(3, 1651, 14.0F, 1.8F, 8, () -> {
		return Ingredient.ofItems(Registry.ITEM.get(new Identifier("techreborn", "sapphire_gem")));
	}),
	PERIDOT(2, 573, 7.0F, 2.4F, 24, () -> {
		return Ingredient.ofItems(Registry.ITEM.get(new Identifier("techreborn", "peridot_gem")));
	}),
	//Adabranium Materials.
	VIBRANIUM(3, 2990, 20F, 10.0F, 12, () -> {
		return Ingredient.ofItems(Registry.ITEM.get(new Identifier("adabraniummod", "vibranium_ingot")));
	}),
	ADAMANTIUM(4, 4280, 35.0F, 16.0F, 10, () -> {
		return Ingredient.ofItems(Registry.ITEM.get(new Identifier("adabraniummod", "adamantium_ingot")));
	}),
	NETHER(2, 381, 8.0F, 2.0F, 15, () -> {
		return Ingredient.ofItems(Items.NETHER_BRICK);
	}),
	//Astromine Materials.
	METITE(2, 981, 14.0F, 5.0F, 5, () -> {
		return Ingredient.ofItems(Registry.ITEM.get(new Identifier("astromine", "metite_ingot")));
	}),
	ASTERITE(5, 2015, 10.0F, 5.0F, 20, () -> {
		return Ingredient.ofItems(Registry.ITEM.get(new Identifier("astromine", "asterite")));
	}),
	STELLUM(5, 2643, 8.0F, 6.0F, 15, () -> {
		return Ingredient.ofItems(Registry.ITEM.get(new Identifier("astromine", "stellum_ingot")));
	}),
	GALAXIUM(6, 3072, 11.0F, 5.0F, 18, () -> {
		return Ingredient.ofItems(Registry.ITEM.get(new Identifier("astromine", "galaxium")));
	}),
	UNIVITE(7, 3918, 12.0F, 6.0F, 22, () -> {
		return Ingredient.ofItems(Registry.ITEM.get(new Identifier("astromine", "univite_ingot")));
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