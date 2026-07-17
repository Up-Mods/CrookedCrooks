package io.github.ennuil.crooked_crooks.entity;

import io.github.ennuil.crooked_crooks.init.CrookedAttributes;
import io.github.ennuil.crooked_crooks.init.CrookedMobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;

import java.util.EnumSet;

public class HerdGoal extends Goal {
	private static final TargetingConditions HERD_TARGETTING = TargetingConditions.forNonCombat().ignoreLineOfSight();
	private final TargetingConditions targetingConditions;
	protected final PathfinderMob mob;
	private final double speedModifier;
	protected Player player;

	public HerdGoal(PathfinderMob mob, double speedModifier) {
		this.mob = mob;
		this.speedModifier = speedModifier;
		this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
		this.targetingConditions = HERD_TARGETTING.copy().selector(((entity, level) -> this.shouldFollow(entity)));
	}

	private boolean shouldFollow(LivingEntity entity) {
		return entity.hasEffect(CrookedMobEffects.SHEPHERDS_TOUCH.holder());
	}

	@Override
	public boolean canUse() {
		this.player = getServerLevel(this.mob).getNearestPlayer(this.targetingConditions.range(this.mob.getAttributeValue(CrookedAttributes.HERDING_RANGE.holder())), this.mob);
		return this.player != null;
	}

	@Override
	public void stop() {
		this.player = null;
		this.mob.getNavigation().stop();
	}

	@Override
	public void tick() {
		this.mob.getLookControl().setLookAt(this.player, (float) (this.mob.getMaxHeadYRot() + 20), (float) this.mob.getMaxHeadXRot());
		if (this.mob.distanceToSqr(this.player) < 6.25) {
			this.mob.getNavigation().stop();
		} else {
			this.mob.getNavigation().moveTo(this.player, this.speedModifier);
		}
	}
}
