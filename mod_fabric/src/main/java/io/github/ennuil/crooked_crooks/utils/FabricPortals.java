package io.github.ennuil.crooked_crooks.utils;

import com.google.auto.service.AutoService;
import io.github.ennuil.crooked_crooks.entity.ModAttributes;
import io.github.ennuil.crooked_crooks.entity.effects.ModMobEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.Attribute;

@AutoService(Portals.class)
public class FabricPortals implements Portals {
	@Override
	public Holder<Attribute> getHerdingRange() {
		return ModAttributes.HERDING_RANGE;
	}

	@Override
	public Holder<MobEffect> getShepherdsTouchEffect() {
		return ModMobEffects.SHEPHERDS_TOUCH;
	}
}
