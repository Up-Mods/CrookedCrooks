package io.github.ennuil.crooked_crooks.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class CrookedCrooksDatagen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		var pack = generator.createPack();

		pack.addProvider(CrookedCrooksTagGenerator::new);
		pack.addProvider(CrookedCrooksRecipeGenerator::new);
	}
}
