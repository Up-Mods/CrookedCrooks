package io.github.ennuil.crooked_crooks.entity.effects;

import io.github.ennuil.crooked_crooks.data.CrookedTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class ShepherdMobEffect extends MobEffect {
	public ShepherdMobEffect(MobEffectCategory category, int color) {
		super(category, color);
	}

	@Override
	public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
		return entity.isHolding(stack -> stack.is(CrookedTags.Items.CROOKS));
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return duration % 20 == 0;
	}
}
