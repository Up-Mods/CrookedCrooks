package io.github.ennuil.crooked_crooks.neo.client;

import com.google.auto.service.AutoService;
import dev.upcraft.sparkweave.api.entrypoint.ClientEntryPoint;
import dev.upcraft.sparkweave.api.platform.ModContainer;
import io.github.ennuil.crooked_crooks.satire_config.SatireConfig;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@AutoService(ClientEntryPoint.class)
public class ClientInit implements ClientEntryPoint {

	@Override
	public void onInitializeClient(ModContainer mod) {
		var neoforgeMod = ModList.get().getModContainerById(mod.metadata().id()).orElseThrow();
		neoforgeMod.registerExtensionPoint(IConfigScreenFactory.class, (_, screen) -> SatireConfig.wrapConfigScreen(screen));
	}
}
