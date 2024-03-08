plugins {
    id("io.freefair.lombok") version("8.6")
}

base.archivesName.set(project.ext.get("modName")!!.toString())

loom.runConfigs["client"].isIdeConfigGenerated = true


dependencies {
    minecraft("com.mojang:minecraft:1.20.4")
    mappings("net.fabricmc:yarn:1.20.4+build.3:v2")
    modImplementation("net.fabricmc:fabric-loader:0.15.7")

    modImplementation("net.fabricmc.fabric-api:fabric-api:0.96.4+1.20.4")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    jar {
        from("LICENSE") {
            rename { "${it}_${project.base.archivesName.get()}" }
        }
    }
}