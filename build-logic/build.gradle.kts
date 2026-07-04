plugins {
	idea
	`kotlin-dsl`
	`maven-publish`
	`version-catalog`
}

repositories {
	gradlePluginPortal()
	mavenCentral()
}

dependencies {
	compileOnly(gradleApi())
	compileOnly(gradleKotlinDsl())
}

catalog {
	versionCatalog {
		from(files("../gradle/libs.versions.toml"))
	}
}
