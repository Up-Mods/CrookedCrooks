plugins {
	idea
	`maven-publish`
	alias(libs.plugins.idea.ext)
	alias(libs.plugins.moddevgradle) apply false
	alias(libs.plugins.fabric.loom) apply false
}

val tag = providers.environmentVariable("TAG")
version = tag.orElse("0.0.0-development").get()

println("Building ${project.name} $version")

providers.environmentVariable("MAVEN_UPLOAD_URL").orNull?.let { url ->
	publishing {
		repositories {
			maven(uri(url)) {
				credentials {
					username = providers.environmentVariable("MAVEN_UPLOAD_USERNAME").orNull
					password = providers.environmentVariable("MAVEN_UPLOAD_PASSWORD").orNull
				}
			}
		}
	}
}

// IDEA no longer automatically downloads sources/javadoc jars for dependencies, so we need to explicitly enable the behavior.
idea {
	module {
		isDownloadSources = true
	}
}
