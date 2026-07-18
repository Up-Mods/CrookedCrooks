package io.github.ennuil.crooked_crooks.datagen.client;

import dev.upcraft.sparkweave.api.datagen.ContextAwarePackOutput;
import dev.upcraft.sparkweave.api.datagen.TranslationBuilder;
import dev.upcraft.sparkweave.api.datagen.provider.client.SparkweaveLanguageProvider;
import io.github.ennuil.crooked_crooks.data.CrookedTags;
import io.github.ennuil.crooked_crooks.init.CrookedItems;
import io.github.ennuil.crooked_crooks.init.CrookedMobEffects;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class CrookedBrazilianLanguageProvider extends SparkweaveLanguageProvider {

	public CrookedBrazilianLanguageProvider(ContextAwarePackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture, "pt_br");
	}

	@Override
	public void generateTranslations(HolderLookup.Provider registries, TranslationBuilder builder) {
		builder.item(CrookedItems.BONE_CROOK, "Cajado de osso");
		builder.item(CrookedItems.WOODEN_CROOK, "Cajado de madeira");
		builder.item(CrookedItems.STONE_CROOK, "Cajado de pedra");
		builder.item(CrookedItems.IRON_CROOK, "Cajado de ferro");

		//FIXME missing translations

//		TL.attribute(builder, CrookedAttributes.HERDING_RANGE, "Crook Herding Range");

		TL.mobEffect(builder, CrookedMobEffects.SHEPHERDS_TOUCH, "Toque do pastor");

		TL.tag(builder, CrookedTags.Items.CROOKS, "Cajados");
		TL.tag(builder, CrookedTags.Items.BONE_TOOL_MATERIALS, "Materiais para ferramentas de osso");

		TL.tag(builder, CrookedTags.Blocks.MINEABLE_WITH_CROOK, "Minerável com cajado");
//		TL.tag(builder, CrookedTags.Blocks.MULTIPLE_DROOPS_WHEN_CROOKED, "Multiple Drops when mined with Crook");
	}
}
