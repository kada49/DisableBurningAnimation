plugins {
  id("gg.essential.multi-version")
  id("gg.essential.defaults")
  id("io.freefair.lombok") version "9.5.0"
}

version = property("modVersion")!!
group = property("modGroup")!!

base {
	archivesName.set(property("modName")!!.toString() + "-$platform")
}


dependencies {
	modImplementation("net.fabricmc:fabric-loader:0.16.14")
	modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_version")}")
}


tasks {
	processResources {
		inputs.property("version", project.version)

        filesMatching("fabric.mod.json") {
            expand(
                "version" to project.version,
                "mcVersion" to platform.mcVersionStr,
                "javaVersion" to platform.javaVersion.toString(),
            )
        }

        filesMatching("disableburninganimation.mixins.json") {
            expand("javaVersion" to "JAVA_${platform.javaVersion}")
		}
	}

	withType<JavaCompile>().configureEach {
		options.release.set(platform.javaVersion.toString().toInt())
	}

	java {
		withSourcesJar()

		sourceCompatibility = platform.javaVersion
		targetCompatibility = platform.javaVersion
	}

	jar {
		from("LICENSE") {
			rename { "${it}_${project.base.archivesName.get()}" }
		}
	}
}
