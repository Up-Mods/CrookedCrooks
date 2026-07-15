plugins {
	alias(libs.plugins.idea.ext)
	alias(libs.plugins.moddevgradle) apply false
	alias(libs.plugins.fabric.loom) apply false
}

val tag = providers.environmentVariable("TAG")
version = tag.orElse("0.0.0-development").get()

println("Building ${project.name} $version")

// IDEA no longer automatically downloads sources/javadoc jars for dependencies, so we need to explicitly enable the behavior.
idea {
	module {
		isDownloadSources = true
	}
}
