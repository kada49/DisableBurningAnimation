plugins {
	id("fabric-loom") version "1.5-SNAPSHOT"
	id("io.freefair.lombok") version "8.6"
}

version = property("modVersion")!!
group = property("modGroup")!!

base {
	archivesName.set(property("modName")!!.toString())
}

dependencies {
	minecraft("com.mojang:minecraft:${property("minecraft_version")!!}")
	mappings("net.fabricmc:yarn:${property("yarn_mappings")}:v2")
	modImplementation("net.fabricmc:fabric-loader:${property("loader_version")}")

	modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_version")}")
}


tasks {
	processResources {
		inputs.property("version", project.version)

		filesMatching("fabric.mod.json") {
			expand("version" to project.version)
		}
	}

	withType<JavaCompile>().configureEach {
		options.release.set(17)
	}

	java {
		withSourcesJar()

		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}

	jar {
		from("LICENSE") {
			rename { "${it}_${project.base.archivesName.get()}" }
		}
	}
}
