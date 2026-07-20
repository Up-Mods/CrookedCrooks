package io.github.ennuil.crooked_crooks.datagen.client;

import dev.upcraft.sparkweave.api.datagen.ContextAwarePackOutput;
import dev.upcraft.sparkweave.api.datagen.TranslationBuilder;
import dev.upcraft.sparkweave.api.datagen.provider.client.SparkweaveLanguageProvider;
import io.github.ennuil.crooked_crooks.data.CrookedTags;
import io.github.ennuil.crooked_crooks.init.CrookedAttributes;
import io.github.ennuil.crooked_crooks.init.CrookedItems;
import io.github.ennuil.crooked_crooks.init.CrookedMobEffects;
import net.minecraft.core.HolderLookup;
import net.minecraft.locale.Language;

import java.util.concurrent.CompletableFuture;

public class CrookedEnglishLanguageProvider extends SparkweaveLanguageProvider {

	public CrookedEnglishLanguageProvider(ContextAwarePackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture, Language.DEFAULT);
	}

	@Override
	public void generateTranslations(HolderLookup.Provider registries, TranslationBuilder builder) {
		builder.item(CrookedItems.BONE_CROOK, "Bone Crook");
		builder.item(CrookedItems.WOODEN_CROOK, "Wooden Crook");
		builder.item(CrookedItems.STONE_CROOK, "Stone Crook");
		builder.item(CrookedItems.IRON_CROOK, "Iron Crook");

		builder.attribute(CrookedAttributes.HERDING_RANGE, "Crook Herding Range");

		builder.mobEffect(CrookedMobEffects.SHEPHERDS_TOUCH, "Shepherd's Touch");

		// FIXME en_us tags will be redundant once implemented in Sparkweave tag generator
		builder.tag(CrookedTags.Items.CROOKS, "Crooks");
		builder.tag(CrookedTags.Items.BONE_TOOL_MATERIALS, "Bone Tool Materials");

		builder.tag(CrookedTags.Blocks.MINEABLE_WITH_CROOK, "Mineable with Crook");
		builder.tag(CrookedTags.Blocks.MULTIPLE_DROOPS_WHEN_CROOKED, "Multiple Drops when mined with Crook");
	}
}
