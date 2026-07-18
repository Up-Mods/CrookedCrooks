package io.github.ennuil.crooked_crooks.datagen.client;

import dev.upcraft.sparkweave.api.datagen.TranslationBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Util;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.Attribute;

import java.util.function.Supplier;

// TODO implement in Sparkweave
public class TL {

	public static void tag(TranslationBuilder builder, TagKey<?> tagKey, String translation) {
		var key = Util.makeDescriptionId("tag.%s".formatted(tagKey.registry().identifier().toShortLanguageKey()), tagKey.location());
		builder.add(key, translation);
	}

	public static void mobEffect(TranslationBuilder builder, Supplier<MobEffect> effect, String translation) {
		builder.add(effect.get().getDescriptionId(), translation);
	}

	public static void attribute(TranslationBuilder builder, Supplier<Attribute> attribute, String translation) {
		builder.add(attribute.get().getDescriptionId(), translation);
	}
}
