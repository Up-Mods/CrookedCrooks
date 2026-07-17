package io.github.ennuil.crooked_crooks.init;

import dev.upcraft.sparkweave.api.registry.RegistryHandler;
import dev.upcraft.sparkweave.api.registry.RegistrySupplier;
import io.github.ennuil.crooked_crooks.CrookedCrooks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class CrookedAttributes {

	public static final RegistryHandler<Attribute> ATTRIBUTES = RegistryHandler.create(Registries.ATTRIBUTE, CrookedCrooks.MOD_NAMESPACE);

	public static final RegistrySupplier<Attribute> HERDING_RANGE = ATTRIBUTES.register("herding_range", () -> new RangedAttribute("attribute.crooked_crooks.name.herding_range", 20.0, 0.0, 2048.0));
}
