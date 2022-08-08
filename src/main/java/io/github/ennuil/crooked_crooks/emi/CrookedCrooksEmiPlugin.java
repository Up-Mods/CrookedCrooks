package io.github.ennuil.crooked_crooks.emi;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.quiltmc.qsl.tag.api.TagRegistry;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import io.github.ennuil.crooked_crooks.CrookedCrooksMod;
import it.unimi.dsi.fastutil.ints.Int2ReferenceAVLTreeMap;
import net.minecraft.block.Block;
import net.minecraft.util.Holder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

// FIXME - what the heck? Investigate why the REA is not being updated before the EMI plugin
public class CrookedCrooksEmiPlugin implements EmiPlugin {
	private static final EmiStack CROOK_ICON = EmiStack.of(CrookedCrooksMod.WOODEN_CROOK_ITEM);
	public static final EmiRecipeCategory CROOK_RECIPE_CATEGORY = new EmiRecipeCategory(new Identifier("crooked_crooks", "emi_recipe_category"), CROOK_ICON);

	@Override
	public void register(EmiRegistry registry) {
		registry.addCategory(CROOK_RECIPE_CATEGORY);
		registry.addWorkstation(CROOK_RECIPE_CATEGORY, EmiIngredient.of(CrookedCrooksMod.CROOKS));

		SortedMap<Integer, List<Block>> reverseMap = new Int2ReferenceAVLTreeMap<>();

		CrookedCrooksMod.CROOK_EFFECTIVE.entryIterator().forEachRemaining(entry -> {
			System.out.println(entry.entry());
			if (reverseMap.containsKey(entry.value())) {
				reverseMap.get(entry.value()).add(entry.entry());
			} else {
				List<Block> list = new ArrayList<>();
				list.add(entry.entry());
				reverseMap.put(entry.value(), list);
			}
		});

		CrookedCrooksMod.CROOK_EFFECTIVE.tagEntryIterator().forEachRemaining(entry -> {
			for (Holder<Block> holder : TagRegistry.getTag(entry.tag())) {
				if (holder.getKey().isPresent()) {
					var block = Registry.BLOCK.get(holder.getKey().get());
					System.out.println(block);

					if (reverseMap.containsKey(entry.value())) {
						reverseMap.get(entry.value()).add(block);
					} else {
						List<Block> list = new ArrayList<>();
						list.add(block);
						reverseMap.put(entry.value(), list);
					}
				}
			}
		});

		reverseMap.forEach((multiplier, blocks) -> {
			System.out.println(multiplier);
			registry.addRecipe(new CrookDropEfficiencyEmiRecipe(multiplier, blocks));
		});
	}
}
