package io.github.ennuil.crooked_crooks.utils;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.Attribute;

import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;
import java.util.ServiceLoader;

public interface Portals {
	Holder<Attribute> getHerdingRange();

	Holder<MobEffect> getShepherdsTouchEffect();

	Portals INSTANCE = ServiceLoader.load(Portals.class, Portals.class.getClassLoader()).findFirst().orElseThrow(() -> new NoSuchElementException("Unable to load %s service!".formatted(Portals.class.getName())));
}
