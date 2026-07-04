@file:Suppress("UnstableApiUsage")

import dev.upcraft.gradle.multiloader.applyMcGradleConventions

plugins {
	id("dev.upcraft.gradle.multiloader.multiloader-common")
	id("net.fabricmc.fabric-loom-companion")
	id("net.neoforged.moddev")
}
applyMcGradleConventions("common")

neoForge.neoFormVersion = libs.versions.neoform.get()

dependencies {
	compileOnly(libs.bundles.mixin)
	compileOnly(libs.jetbrains.annotations)
	compileOnly(libs.autoservice.annotations)

	testCompileOnly(libs.junit.api)
	testCompileOnly(libs.neoforge.testframework)
}

neoForge {
	validateAccessTransformers.set(true)
//	accessTransformers {
//		val atFile = file("src/main/resources/META-INF/accesstransformer.cfg")
//		from(atFile)
//		publish(atFile)
//	}

//	interfaceInjectionData {
//		val interfacesFile = file("src/main/resources/META-INF/interfaces.json")
//		from(interfacesFile)
//		publish(interfacesFile)
//	}
}

val commonJava = configurations.consumable("commonJava")
val commonResources = configurations.consumable("commonResources")

val testmodCommonResources = configurations.consumable("testmodCommonResources")
val testmodCommonJava = configurations.consumable("testmodCommonJava")

artifacts {
	add(commonJava.name, sourceSets["main"].java.sourceDirectories.singleFile)
	add(commonResources.name, sourceSets["main"].resources.sourceDirectories.singleFile)
}
