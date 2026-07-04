@file:Suppress("UnstableApiUsage")

import dev.upcraft.gradle.multiloader.applyMcGradleConventions
import java.util.*

plugins {
	id("dev.upcraft.gradle.multiloader.multiloader-loader")
	id("net.neoforged.moddev")
}
applyMcGradleConventions("neoforge")

val modId = providers.gradleProperty("mod_id").get()

// need this before dependencies because it configures the plugin and creates additionalRuntimeClasspath configuration
neoForge.version = libs.versions.neoforge.get()

val localRuntime = configurations.dependencyScope("localRuntime")
configurations.runtimeClasspath.configure { extendsFrom(localRuntime) }

dependencies {
//	interfaceInjectionData(project(":${rootProject.name}-common"))
//	accessTransformers(project(":${rootProject.name}-common"))
	compileOnly(libs.autoservice.annotations)
	annotationProcessor(libs.autoservice)

	testImplementation(libs.neoforge.testframework)
}

neoForge {
	mods {
		// define mod <-> source bindings
		// these are used to tell the game which sources are for which mod
		// mostly optional in a single mod project
		// but multi mod projects should define one per mod
		register(modId) {
			sourceSet(sourceSets["main"])
			sourceSet(project(":${rootProject.name}-common").sourceSets["main"])
		}
	}

	unitTest {
		enable()

		testedMod = mods[modId]
		loadedMods = listOf(mods[modId])
	}

	runs {
		register("client") {
			client()
			devLogin = true
			gameDirectory = file("run/client")
			systemProperty("neoforge.enabledGameTestNamespaces", modId)

			sourceSet = sourceSets["main"]
			loadedMods = listOf(mods[modId])
		}

		register("server") {
			server()
			gameDirectory = file("run/server")
			systemProperty("neoforge.enabledGameTestNamespaces", modId)

			sourceSet = sourceSets["main"]
			loadedMods = listOf(mods[modId])

			programArgument("--nogui")
		}

		register("data") {
			clientData()
			gameDirectory = file("run/data")

			programArguments.addAll(
				"--mod", modId,
				"--all",
				"--flat",
				"--output", file("src/main/generated").absolutePath,
				"--existing", file("src/main/resources").absolutePath
			)
			sourceSet = sourceSets["main"]
			loadedMods = listOf(mods[modId])
		}

		configureEach {
			logLevel = org.slf4j.event.Level.DEBUG
			systemProperty("forge.logging.markers", "REGISTRIES")

			ideName = "NeoForge ${name.replaceFirstChar { it.titlecase(Locale.ROOT) }}"
		}
	}
}

sourceSets["main"].resources { srcDir("src/main/generated") }
