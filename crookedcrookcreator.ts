// ₢³ (Crooked Crook Creator)
// Creates a crook from a JSON definition

import * as filepath from "https://deno.land/std/path/mod.ts";

interface CrookDefinition {
    crookId: string;
    modId: string;
    baseItem?: string;
    ingredient: string;
    criteriaName: string;
    modCondition?: string;
    dependsOnItem?: boolean;
    craftingMethod?: string;
}

interface CrookIndex {
    crooks: string[];
}

interface Tag {
    replace: boolean;
    values: (string | OptionalTagEntry)[];
}

interface OptionalTagEntry {
    id: string;
    required: boolean;
}

if (Deno.args.length >= 1) {
    if (Deno.args[0] === "create") {
        const indexData = await Deno.readTextFile(filepath.resolve("crook_src/index.json"));
        const index: CrookIndex = JSON.parse(indexData);
        const crookTagPath = filepath.resolve("src/main/resources/data/crookedcrooks/tags/items/crooks.json");
        const crookTag: Tag = {
            replace: false,
            values: []
        };

        for (const path of index.crooks) {
            const crookDefData = await Deno.readTextFile(filepath.resolve("crook_src/", ...path.concat(".json").split("/"),));
            const crookDef: CrookDefinition = JSON.parse(crookDefData);

            if (crookDef.modId === "minecraft") {
                crookTag.values.push(`crookedcrooks:${crookDef.crookId}`);
            } else {
                crookTag.values.push({
                    id: `crookedcrooks:${crookDef.crookId}`,
                    required: false
                });
            }

            createCrook(crookDef);
        }

        await Deno.writeTextFile(crookTagPath, JSON.stringify(crookTag, null, 2)); 
    } else if (Deno.args[0] === "create_from") {
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

interface SmithingRecipe {
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

interface ShapedCraftingRecipe {
    type: string;
    pattern: string[];
    key: {
        [key: string]: {
            item: string;
        } | {
            tag: string;
        };
    };
    result: {
        count: number;
        item: string;
    };
}

async function createRecipe(crookDef: CrookDefinition) {
    if (crookDef.craftingMethod !== undefined) {
        const recipePath = filepath.resolve(`src/main/resources/data/crookedcrooks/recipes/${crookDef.crookId}.json`);
        const recipeKey = crookDef.ingredient.startsWith("#")
        ?   {
                tag: crookDef.ingredient.slice(1)
            }
        :   {
                item: crookDef.ingredient
            };
        const recipeJson: ShapedCraftingRecipe = {
            type: "crafting_shaped",
            pattern: [
                "ss",
                " s",
                " s"
            ],
            key: {
                s: recipeKey
            },
            result: {
                count: 1,
                item: `crookedcrooks:${crookDef.crookId}`
            }
        };

        await Deno.writeTextFile(recipePath, JSON.stringify(recipeJson, null, 2)); 
    } else {
        const recipePath = filepath.resolve(`src/main/resources/data/crookedcrooks/recipes/${crookDef.crookId}_smithing.json`);
        const recipeBase = crookDef.baseItem !== undefined ? crookDef.baseItem : "crookedcrooks:wooden_crook";
        const recipeAddition = crookDef.ingredient.startsWith("#")
        ?   {
                tag: crookDef.ingredient.slice(1)
            }
        :   {
                item: crookDef.ingredient
            };
        const recipeJson: SmithingRecipe = {
            type: "smithing",
            base: {
                item: recipeBase
            },
            addition: recipeAddition,
            result: {
                item: `crookedcrooks:${crookDef.crookId}`
            }
        };

        await Deno.writeTextFile(recipePath, JSON.stringify(recipeJson, null, 2)); 
    }
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
    requirements: (string | string[])[]
}

async function createRecipeAdvancement(crookDef: CrookDefinition) {
    let recipeName = `${crookDef.crookId}`;
    if (crookDef.craftingMethod === undefined || crookDef.craftingMethod === "smithing") {
        recipeName = recipeName.concat("_smithing");
    }
    const advancementPath = filepath.resolve(`src/main/resources/data/crookedcrooks/advancements/recipes/${recipeName}.json`);
    const advancementConditions = crookDef.ingredient.startsWith("#")
    ?   {
            tag: crookDef.ingredient.slice(1)
        }
    :   {
            items: [
                crookDef.ingredient
            ]
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
                recipe: `crookedcrooks:${recipeName}`
            }
        },
        enumerable: true
    });

    const advancementJson: RecipeAdvancement = {
        parent: "crookedcrooks:recipes/root",
        rewards: {
            recipes: [`crookedcrooks:${recipeName}`]
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
    if (crookDef.modId === "minecraft") return;
    let recipeName = `${crookDef.crookId}`;
    if (crookDef.craftingMethod === undefined || crookDef.craftingMethod === "smithing") {
        recipeName = recipeName.concat("_smithing");
    }
    
    const recipeMcmetaPath = filepath.resolve(`src/main/resources/data/crookedcrooks/recipes/${recipeName}.json.mcmeta`);
    const advancementMcmetaPath = filepath.resolve(`src/main/resources/data/crookedcrooks/advancements/recipes/${recipeName}.json.mcmeta`);
    
    const condition = {};
    if (crookDef.modCondition !== undefined) {
        Object.defineProperty(condition, `crookedcrooks:${crookDef.modCondition}`, {
            value: crookDef.modId,
            enumerable: true
        });
    } else if (crookDef.dependsOnItem === true) {
        Object.defineProperty(condition, "libcd:item_exists", {
            value: `crookedcrooks:${crookDef.crookId}`,
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