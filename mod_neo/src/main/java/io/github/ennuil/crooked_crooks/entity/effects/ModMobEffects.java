package io.github.ennuil.crooked_crooks.entity.effects;

import io.github.ennuil.crooked_crooks.utils.ModUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.CommonColors;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMobEffects {
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, ModUtils.MOD_NAMESPACE);

	public static final Holder<MobEffect> SHEPHERDS_TOUCH = MOB_EFFECTS.register(
		"shepherds_touch",
		() -> new ShepherdMobEffect(MobEffectCategory.BENEFICIAL, CommonColors.WHITE)
	);
}
