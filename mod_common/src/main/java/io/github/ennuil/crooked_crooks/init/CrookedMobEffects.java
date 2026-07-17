package io.github.ennuil.crooked_crooks.init;

import dev.upcraft.sparkweave.api.registry.RegistryHandler;
import dev.upcraft.sparkweave.api.registry.RegistrySupplier;
import io.github.ennuil.crooked_crooks.CrookedCrooks;
import io.github.ennuil.crooked_crooks.entity.effects.ShepherdMobEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.CommonColors;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class CrookedMobEffects {

	public static final RegistryHandler<MobEffect> MOB_EFFECTS = RegistryHandler.create(Registries.MOB_EFFECT, CrookedCrooks.MOD_NAMESPACE);

	public static final RegistrySupplier<MobEffect> SHEPHERDS_TOUCH = MOB_EFFECTS.register("shepherds_touch", () -> new ShepherdMobEffect(MobEffectCategory.BENEFICIAL, CommonColors.WHITE));
}
