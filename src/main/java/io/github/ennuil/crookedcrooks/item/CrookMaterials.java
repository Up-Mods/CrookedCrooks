package io.github.ennuil.crookedcrooks.item;

import java.util.function.Supplier;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.Lazy;

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
		return Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("c:bronze_ingots")));
	}),
	RUBY(2, 750, 6.0F, 1.5F, 10, () -> {
		return Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("c:rubies")));
	}),
	SAPPHIRE(3, 1000, 7.0F, 1.5F, 12, () -> {
		return Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("c:sapphires")));
	}),
	PERIDOT(2, 750, 7.0F, 1.5F, 12, () -> {
		return Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("c:peridot_gems")));
	}),
	//Applied Energistics 2 Materials
	CERTUS_QUARTZ(ToolMaterials.IRON.getMiningLevel(), ToolMaterials.IRON.getDurability(), ToolMaterials.IRON.getMiningLevel(), ToolMaterials.IRON.getAttackDamage(), ToolMaterials.IRON.getEnchantability(), () -> {
		return Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("appliedenergistics2:crystals/certus_quartz")));
	}),
	NETHER_QUARTZ(ToolMaterials.IRON.getMiningLevel(), ToolMaterials.IRON.getDurability(), ToolMaterials.IRON.getMiningLevel(), ToolMaterials.IRON.getAttackDamage(), ToolMaterials.IRON.getEnchantability(), () -> {
		return Ingredient.fromTag(TagFactory.ITEM.create(new Identifier("c:quartz")));
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