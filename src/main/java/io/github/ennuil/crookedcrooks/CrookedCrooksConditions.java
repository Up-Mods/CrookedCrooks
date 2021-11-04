package io.github.ennuil.crookedcrooks;

import io.github.cottonmc.libcd.api.CDSyntaxError;
import io.github.cottonmc.libcd.api.LibCDInitializer;
import io.github.cottonmc.libcd.api.condition.ConditionManager;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

public class CrookedCrooksConditions implements LibCDInitializer {
    @Override
    public void initConditions(ConditionManager manager) {
        manager.registerCondition(new Identifier("crookedcrooks", "bronze_preference"), value -> {
            if (value instanceof String valueString) {
                if (FabricLoader.getInstance().isModLoaded(valueString)) {
                    if (!valueString.contentEquals("indrev") && FabricLoader.getInstance().isModLoaded("indrev")) {
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
