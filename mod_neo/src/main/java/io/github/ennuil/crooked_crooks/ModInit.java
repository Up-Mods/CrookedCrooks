package io.github.ennuil.crooked_crooks;

import io.github.ennuil.crooked_crooks.entity.ModAttributes;
import io.github.ennuil.crooked_crooks.entity.effects.ModMobEffects;
import io.github.ennuil.crooked_crooks.item.ModItems;
import io.github.ennuil.crooked_crooks.satire_config.SatireConfig;
import io.github.ennuil.crooked_crooks.utils.ModUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(ModUtils.MOD_NAMESPACE)
public class ModInit {
	public ModInit(IEventBus bus, ModContainer mod) {
		SatireConfig.setConfigPath(FMLPaths.CONFIGDIR.get().resolve("crooked_crooks"));
		SatireConfig.init();
		mod.registerExtensionPoint(IConfigScreenFactory.class, (mod2, screen) -> SatireConfig.wrapConfigScreen(screen));
		ModItems.ITEMS.register(bus);
		ModAttributes.ATTRIBUTES.register(bus);
		ModMobEffects.MOB_EFFECTS.register(bus);
	}
}
