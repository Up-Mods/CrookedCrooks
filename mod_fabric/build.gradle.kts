@file:Suppress("UnstableApiUsage")

import dev.upcraft.gradle.multiloader.applyMcGradleConventions
import net.fabricmc.loom.task.LoomTasks
import org.jetbrains.gradle.ext.runConfigurations
import org.jetbrains.gradle.ext.settings

plugins {
	id("dev.upcraft.gradle.multiloader.multiloader-loader")
	id("org.jetbrains.gradle.plugin.idea-ext")
	id("net.fabricmc.fabric-loom")
}
applyMcGradleConventions("fabric")

val modId: String = providers.gradleProperty("mod_id").get()

repositories {
	exclusiveContent {
		forRepository {
			maven(uri("https://maven.covers1624.net")) {
				name = "Covers1624"
			}
		}
		filter {
			includeGroup("net.covers1624")
		}
	}
}

dependencies {
	localRuntime(libs.devlogin)
	minecraft(libs.minecraft)

	compileOnly(libs.autoservice.annotations)
	annotationProcessor(libs.autoservice)

	implementation(libs.fabric.loader)
	implementation(libs.fabric.api)

	implementation(libs.sparkweave.fabric)

	compileOnly(libs.modmenu.fabric) {
		isTransitive = false
	}
	localRuntime(libs.modmenu.fabric) {
		isTransitive = false
	}

	testImplementation(libs.junit.api)
	testImplementation(libs.neoforge.testframework)
	testRuntimeOnly(libs.junit.launcher)
	testRuntimeOnly(libs.junit.engine)
}

loom {
	mods {
		create(modId) {
			// Tell Loom about each source set used by your mod here. This ensures that your mod's classes are properly transformed by Loader.
			sourceSet(sourceSets["main"])
			sourceSet(project(":${rootProject.name}-common").sourceSets["main"])
		}
	}

	accessWidenerPath.set(file("src/main/resources/${modId}.classtweaker"))

	runs {
		named("client") {
			client()
			programArguments.addAll(listOf("--launch_target", "net.fabricmc.loader.impl.launch.knot.KnotClient"))
			mainClass = "net.covers1624.devlogin.DevLogin"
			displayName = "Fabric Client"
			runDirectory = file("run/client")
		}

		named("server") {
			server()
			displayName = "Fabric Server"
			runDirectory = file("run/server")
		}

		fabricApi.configureDataGeneration {
			client = true
			modId = "sparkweave"
			strictValidation = true // neoforge '--all' sets '--validate' to true as well
		}

		named("datagen") {
			displayName = "Fabric Data"

			systemProperties.put("sparkweave.datagen.mods", modId)
		}

		configureEach {
			appendProjectPathToDisplayName = false
			systemProperties.put("mixin.debug", "true")

			// register as Gradle runs instead of IDEA runs
			// https://github.com/FabricMC/fabric-loom/issues/1349
			generateRunConfig = false
			rootProject.idea.project.settings.runConfigurations.create<org.jetbrains.gradle.ext.Gradle>(displayName.get()) {
				taskNames = listOf(LoomTasks.getRunConfigTaskName(this@configureEach))
				setProject(project)
			}
		}
	}
}
