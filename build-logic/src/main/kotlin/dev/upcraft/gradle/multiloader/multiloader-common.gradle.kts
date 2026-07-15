@file:Suppress("UnstableApiUsage")

package dev.upcraft.gradle.multiloader

plugins {
	`java-library`
	`maven-publish`
}

version = rootProject.version

val libs = versionCatalogs.named("libs")

val minecraftVersion: String = libs.findVersion("minecraft").orElseThrow().toString()
println("Minecraft: $minecraftVersion")

val javaVersion = libs.findVersion("java").orElseThrow().toString().toInt()
println("Java: $javaVersion")

// FIXME workaround for appdirs transitively requiring newer JNA but we are locked due to MC
libs.findLibrary("jna").ifPresent {
	configurations.configureEach {
		resolutionStrategy.force(it)
	}
}

repositories {
	mavenCentral()

	maven(uri("https://maven.fabricmc.net")) {
		name = "FabricMC"
	}

	maven(uri("https://maven.neoforged.net/releases")) {
		name = "NeoForge"
	}

	maven(uri("https://maven.uuid.gg/releases")) {
		name = "Up-Mods"
	}

	// FIXME currently unavailable, using backup
	// maven(uri("https://maven.terraformersmc.com/releases")) {
	maven(uri("https://maven.gnomecraft.net/releases")) {
		name = "TerraformersMC"
	}
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(javaVersion)
		vendor = JvmVendorSpec.MICROSOFT
	}

	withSourcesJar()
}

tasks.withType<JavaCompile>().configureEach {
	options.release.set(javaVersion)
	options.compilerArgs.add("-Xlint:unchecked")
}

tasks.named<Jar>("jar").configure {

	from(rootProject.file("LICENSE.md")) {
		rename("LICENSE.md", "LICENSE_${rootProject.name}.md")
	}

	manifest.attributes(
		mapOf<String, Any>(
			"Specification-Title" to rootProject.name,
			"Specification-Vendor" to "EnnuiL",
			"Specification-Version" to archiveVersion,

			"Implementation-Title" to project.name,
			"Implementation-Vendor" to "Up",
			"Implementation-Version" to archiveVersion,

			"Built-On-Java" to "${providers.systemProperty("java.vm.version").orNull} (${providers.systemProperty("java.vm.vendor").orNull})",
			"Built-On-Minecraft" to minecraftVersion
		)
	)
}

tasks.named<Jar>("sourcesJar").configure {
	from(rootProject.file("LICENSE.md")) {
		rename("LICENSE.md", "LICENSE_${rootProject.name}.md")
	}
}

tasks.withType<ProcessResources>().configureEach {
	filteringCharset = "UTF-8"
}

tasks.named<ProcessResources>("processResources").configure {
	configureModProperties()
}

publishing {
	publications {
		register("mavenJava", MavenPublication::class) {
			from(components["java"])
		}
	}
}

// Declare capabilities on the outgoing configurations.
// Read more about capabilities here: https://docs.gradle.org/current/userguide/component_capabilities.html#sec:declaring-additional-capabilities-for-a-local-component
listOf("apiElements", "runtimeElements", "sourcesElements").forEach { variant ->
	configurations.named(variant).configure {
		outgoing {
			capability("$group:${project.name}:$version")
			capability("$group:${rootProject.name}:$version")
		}
	}

	publishing {
		publications.withType<MavenPublication>().configureEach {
			suppressPomMetadataWarningsFor(variant)
		}
	}
}
