package io.github.ennuil.crooked_crooks.entity;

import io.github.ennuil.crooked_crooks.utils.ModUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModAttributes {
	public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(BuiltInRegistries.ATTRIBUTE, ModUtils.MOD_NAMESPACE);

	public static final Holder<Attribute> HERDING_RANGE = ATTRIBUTES.register(
		"herding_range",
		() -> new RangedAttribute("attribute.crooked_crooks.name.herding_range", 20.0, 0.0, 2048.0)
	);
}
