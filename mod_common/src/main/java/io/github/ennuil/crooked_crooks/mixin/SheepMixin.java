package io.github.ennuil.crooked_crooks.mixin;

import io.github.ennuil.crooked_crooks.entity.HerdGoal;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Sheep.class)
public abstract class SheepMixin extends Animal {
	private SheepMixin(EntityType<? extends Animal> entityType, Level level) {
		super(entityType, level);
	}

	@Inject(method = "registerGoals", at = @At("TAIL"))
	private void registerHerdGoal(CallbackInfo ci) {
		this.goalSelector.addGoal(3, new HerdGoal(this, 1.25));
	}
}
