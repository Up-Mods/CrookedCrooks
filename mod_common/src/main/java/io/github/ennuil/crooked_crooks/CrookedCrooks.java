package io.github.ennuil.crooked_crooks;


import com.google.auto.service.AutoService;
import dev.upcraft.sparkweave.api.entrypoint.MainEntryPoint;
import dev.upcraft.sparkweave.api.platform.ModContainer;
import dev.upcraft.sparkweave.api.platform.Services;
import dev.upcraft.sparkweave.api.platform.services.RegistryService;
import io.github.ennuil.crooked_crooks.init.CrookedAttributes;
import io.github.ennuil.crooked_crooks.init.CrookedItems;
import io.github.ennuil.crooked_crooks.init.CrookedMobEffects;
import io.github.ennuil.crooked_crooks.satire_config.SatireConfig;
import net.minecraft.resources.Identifier;

@AutoService(MainEntryPoint.class)
public class CrookedCrooks implements MainEntryPoint {
	public static final String MOD_NAMESPACE = "crooked_crooks";

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_NAMESPACE, path);
	}

	@Override
	public void onInitialize(ModContainer mod) {
		SatireConfig.setConfigPath(Services.PLATFORM.getConfigDir().resolve("crooked_crooks"));
		SatireConfig.init();

		var registryService = RegistryService.get();

		CrookedAttributes.ATTRIBUTES.accept(registryService);
		CrookedItems.ITEMS.accept(registryService);
		CrookedMobEffects.MOB_EFFECTS.accept(registryService);
	}
}
