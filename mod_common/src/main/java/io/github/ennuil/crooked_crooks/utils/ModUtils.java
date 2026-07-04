package io.github.ennuil.crooked_crooks.utils;


import net.minecraft.resources.Identifier;

public class ModUtils {
	public static final String MOD_NAMESPACE = "crooked_crooks";

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_NAMESPACE, path);
	}
}
