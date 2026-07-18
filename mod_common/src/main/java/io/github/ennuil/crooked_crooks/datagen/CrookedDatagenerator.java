package io.github.ennuil.crooked_crooks.datagen;

import com.google.auto.service.AutoService;
import dev.upcraft.sparkweave.api.datagen.DataGenerationContext;
import dev.upcraft.sparkweave.api.entrypoint.DataGenerationEntryPoint;
import io.github.ennuil.crooked_crooks.datagen.client.CrookedModelProvider;
import io.github.ennuil.crooked_crooks.datagen.common.CrookedBlockTagProvider;
import io.github.ennuil.crooked_crooks.datagen.common.CrookedItemTagProvider;
import io.github.ennuil.crooked_crooks.datagen.common.CrookedRecipeProvider;

@AutoService(DataGenerationEntryPoint.class)
public class CrookedDatagenerator implements DataGenerationEntryPoint {

	@Override
	public void generate(DataGenerationContext ctx) {
		var pack = ctx.getDefaultPack();

		var blockTags = pack.addProvider(CrookedBlockTagProvider::new);
		pack.addProvider((output, registriesFuture) -> new CrookedItemTagProvider(output, registriesFuture, blockTags));
		pack.addRecipes(CrookedRecipeProvider::new);

		// TODO language
		pack.addProvider(DataGenerationContext::includeClient, CrookedModelProvider::new);
	}
}
