package io.github.ennuil.crooked_crooks;

import org.quiltmc.loader.api.QuiltLoader;

import io.github.cottonmc.libcd.api.CDSyntaxError;
import io.github.cottonmc.libcd.api.LibCDInitializer;
import io.github.cottonmc.libcd.api.condition.ConditionManager;
import net.minecraft.util.Identifier;

public class CrookedCrooksConditions implements LibCDInitializer {
    @Override
    public void initConditions(ConditionManager manager) {
        manager.registerCondition(new Identifier(CrookedCrooksMod.MODID, "bronze_preference"), value -> {
            if (value instanceof String valueString) {
                if (QuiltLoader.isModLoaded(valueString)) {
                    if (!valueString.contentEquals("indrev") && QuiltLoader.isModLoaded("indrev")) {
                        return false;
                    }
                    return true;
                } else {
                    return false;
                }
            }

            throw new CDSyntaxError("bronze_preference must accept a String!");
        });
    }
}
