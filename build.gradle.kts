import dev.architectury.pack200.java.Pack200Adapter

plugins {
    java
    kotlin("jvm") version ("1.8.22")
    id("gg.essential.loom") version ("1.2.8")
    id("dev.architectury.architectury-pack200") version ("0.1.3")
}

val modGroup: String by project
val modName: String by project
val modVersion: String by project

group = modGroup
version = modVersion
base.archivesName.set(modName)

loom.forge.pack200Provider.set(Pack200Adapter())


dependencies {
    // Dependencies required for loom to set up the development environment
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
}