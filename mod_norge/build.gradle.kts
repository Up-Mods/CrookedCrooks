plugins {
	id("mod_conventions_loader")
	alias(libs.plugins.moddevgradle)
}

base.archivesName = "crooked_crooks-neo"

neoForge {
	version = libs.versions.neoforge.get()

	parchment {
		minecraftVersion = libs.versions.minecraft
		mappingsVersion = libs.versions.parchment
	}

	runs {
		register("client") {
			client()
		}
	}

	mods {
		register("crooked_crooks") {
			sourceSet(sourceSets.main.get())
		}
	}
}

tasks.named<ProcessResources>("processResources").configure {
	val version = project.version
	inputs.property("version", version)

	filesMatching("META-INF/neoforge.mods.toml") {
		expand("version" to version)
	}
}
