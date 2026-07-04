pluginManagement {
	repositories {
		gradlePluginPortal()
		mavenCentral()
		maven {
			name = "NeoForge"
			url = uri("https://maven.neoforged.net/releases")
		}
		maven {
			name = "Fabric"
			url = uri("https://maven.fabricmc.net")
		}
	}
}

plugins {
	id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "crooked_crooks"

includeBuild("build-logic")

listOf("common", "fabric", "neo").forEach {
	include("mod_$it")
	project(":mod_$it").name = "${rootProject.name}-$it"
}
