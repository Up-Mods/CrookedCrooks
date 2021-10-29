// ₢³ (Crooked Crook Creator)
// Creates a crook from a JSON definition

import * as filepath from "https://deno.land/std/path/mod.ts";

interface CrookDefinition {
    crookId: string;
    modId: string;
    ingredient: string;
    criteriaName: string;
    modCondition?: string;
}

if (Deno.args.length >= 1) {
    if (Deno.args[0] === "create_from") {
        if (Deno.args.length >= 2) {
            const jsonData = await Deno.readTextFile(filepath.resolve("crook_src/", ...Deno.args[1].concat(".json").split("/"),));
            const crookDef: CrookDefinition = JSON.parse(jsonData);
    
            createCrook(crookDef);
        } else {
            console.error("Insufficient args!");
        }
    } else {
        console.error("Invalid mode!");
    }
} else {
    console.error("Insufficient args!");
}

async function createCrook(crookDef: CrookDefinition) {
    await Promise.allSettled([
        createRecipe(crookDef),
        createRecipeAdvancement(crookDef),
        createMCDictMcmetas(crookDef),
        createItemModels(crookDef)
    ]);
}

interface Recipe {
    type: string;
    base: {
        item: string;
    };
    addition: {
        item: string;
    } | {
        tag: string;
    };
    result: {
        item: string;
    };
}

async function createRecipe(crookDef: CrookDefinition) {
    const recipePath = filepath.resolve(`src/main/resources/data/crookedcrooks/recipes/${crookDef.crookId}_smithing.json`);
    const recipeAddition = crookDef.ingredient.startsWith("#")
    ?   {
            tag: crookDef.ingredient.slice(1)
        }
    :   {
            item: crookDef.ingredient
        };
    const recipeJson: Recipe = {
        type: "minecraft:smithing",
        base: {
            item: "crookedcrooks:wooden_crook"
        },
        addition: recipeAddition,
        result: {
            item: `crookedcrooks:${crookDef.crookId}`
        }
    }
    await Deno.writeTextFile(recipePath, JSON.stringify(recipeJson, null, 2)); 
}

interface RecipeAdvancement {
    parent: string;
    rewards: {
        recipes: string[]
    },
    criteria: {
        [id: string]: {
            trigger: string,
            conditions: Record<string, unknown>
        }
    },
    requirements: string[][]
}

async function createRecipeAdvancement(crookDef: CrookDefinition) {
    const advancementPath = filepath.resolve(`src/main/resources/data/crookedcrooks/advancements/recipes/${crookDef.crookId}_smithing.json`);
    const advancementConditions = crookDef.ingredient.startsWith("#")
    ?   {
            tag: crookDef.ingredient.slice(1)
        }
    :   {
            item: crookDef.ingredient
        };
    const advancementCriteria = {};
    
    Object.defineProperty(advancementCriteria, crookDef.criteriaName, {
        value: {
            trigger: "minecraft:inventory_changed",
            conditions: {
                items: [
                    advancementConditions
                ]
            }
        },
        enumerable: true
    });
    Object.defineProperty(advancementCriteria, "has_the_recipe", {
        value: {
            trigger: "minecraft:recipe_unlocked",
            conditions: {
                recipe: `crookedcrooks:${crookDef.crookId}_smithing`
            }
        },
        enumerable: true
    });

    const advancementJson: RecipeAdvancement = {
        parent: "crookedcrooks:recipes/root",
        rewards: {
            recipes: [`crookedcrooks:${crookDef.crookId}_smithing`]
        },
        criteria: advancementCriteria,
        requirements: [
            [
                crookDef.criteriaName,
                "has_the_recipe"
            ]
        ]
    };
    await Deno.writeTextFile(advancementPath, JSON.stringify(advancementJson, null, 2));
}

async function createMCDictMcmetas(crookDef: CrookDefinition) {
    const recipeMcmetaPath = filepath.resolve(`src/main/resources/data/crookedcrooks/recipes/${crookDef.crookId}_smithing.json.mcmeta`);
    const advancementMcmetaPath = filepath.resolve(`src/main/resources/data/crookedcrooks/advancements/recipes/${crookDef.crookId}_smithing.json.mcmeta`);
    
    const condition = {};
    if (crookDef.modCondition !== undefined) {
        Object.defineProperty(condition, `crookedcrooks:${crookDef.modCondition}`, {
            value: crookDef.modId,
            enumerable: true
        });
    } else {
        Object.defineProperty(condition, "libcd:mod_loaded", {
            value: crookDef.modId,
            enumerable: true
        });
    }

    const mcmetaObject = {
        when: [
            condition
        ]
    };

    const mcmetaJson = JSON.stringify(mcmetaObject, null, 2);

    await Promise.allSettled([
        Deno.writeTextFile(recipeMcmetaPath, mcmetaJson),
        Deno.writeTextFile(advancementMcmetaPath, mcmetaJson)
    ]);
}

interface ItemModel {
    parent: string;
    textures: {
        layer0: string;
    };
}

async function createItemModels(crookDef: CrookDefinition) {
    const itemModelPath = filepath.resolve(`src/main/resources/assets/crookedcrooks/models/item/${crookDef.crookId}.json`);

    const itemModelJson: ItemModel = {
        parent: "item/handheld",
        textures: {
            layer0: `crookedcrooks:item/${crookDef.crookId}`
        }
    }

    await Deno.writeTextFile(itemModelPath, JSON.stringify(itemModelJson, null, 2));
}