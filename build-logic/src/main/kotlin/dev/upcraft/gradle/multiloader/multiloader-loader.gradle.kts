@file:Suppress("UnstableApiUsage")

package dev.upcraft.gradle.multiloader

plugins {
	id("dev.upcraft.gradle.multiloader.multiloader-common")
}

val commonJavaDep = configurations.dependencyScope("commonJavaDep")
val commonJava = configurations.resolvable("commonJava") { extendsFrom(commonJavaDep) }

val commonResourcesDep = configurations.dependencyScope("commonResourcesDep")
val commonResources = configurations.resolvable("commonResources") { extendsFrom(commonResourcesDep) }

dependencies {
	compileOnly(project(":${rootProject.name}-common")) {
		attributes { loaderAttribute("common") }
	}

	commonJavaDep(project(":${rootProject.name}-common", "commonJava"))
	commonResourcesDep(project(":${rootProject.name}-common", "commonResources"))
}

tasks.named<JavaCompile>("compileJava").configure {
	dependsOn(commonJava)
	source(commonJava)
}

tasks.named<ProcessResources>("processResources").configure {
	dependsOn(commonResources)
	from(commonResources)
}

tasks.named<Javadoc>("javadoc").configure {
	dependsOn(commonJava)
	source(commonJava)
}

tasks.named<Jar>("sourcesJar").configure {
	dependsOn(commonJava, commonResources)
	from(commonJava, commonResources)
}
