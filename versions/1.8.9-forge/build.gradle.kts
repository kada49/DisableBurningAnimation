import dev.architectury.pack200.java.Pack200Adapter

base.archivesName.set(project.ext.get("modName")!!.toString())

loom {
    runConfigs["client"].isIdeConfigGenerated = true
    forge.pack200Provider.set(Pack200Adapter())
}


dependencies {
    minecraft("com.mojang:minecraft:1.8.9")
    mappings("de.oceanlabs.mcp:mcp_stable:22-1.8.9")
    forge("net.minecraftforge:forge:1.8.9-11.15.1.2318-1.8.9")
}

tasks {
    processResources {
        inputs.property("version", project.version)

        filesMatching("mcmod.info") {
            expand("version" to project.version)
        }
    }

    withType<JavaCompile> {
        options.release.set(8)
        options.encoding = "UTF-8"
    }

    jar {
        from("LICENSE") {
            rename { "${it}_${project.base.archivesName.get()}" }
        }
    }
}