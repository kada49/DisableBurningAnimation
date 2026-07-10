pluginManagement {
  repositories {
    gradlePluginPortal()
    mavenCentral()
    maven("https://repo.essential.gg/repository/maven-public")
    maven("https://maven.architectury.dev/")
		maven("https://maven.fabricmc.net/")
    maven("https://maven.minecraftforge.net")
  }

  plugins {
    val egtVersion = "0.7.2";
    id("gg.essential.multi-version.root") version(egtVersion)
    id("gg.essential.multi-version.api-validation") version(egtVersion)
    id("gg.essential.loom")
  }
}

listOf(
  "fabric-1.20",
  "fabric-1.20.1",
  "fabric-1.20.2",
  "fabric-1.20.3",
  "fabric-1.20.4",
  "fabric-1.20.5",
  "fabric-1.20.6",
  "fabric-1.21",
  "fabric-1.21.1",
  "fabric-1.21.2",
  "fabric-1.21.3",
  "fabric-1.21.4",
  "fabric-1.21.5",
  "fabric-1.21.6",
  "fabric-1.21.7",
  "fabric-1.21.8",
  "fabric-1.21.9",
  "fabric-1.21.10",
  "fabric-1.21.11",
  "fabric-26.1",
  "fabric-26.1.1",
  "fabric-26.1.2",
  "fabric-26.2",
).forEach { version ->
  include(":$version")
  project(":$version").apply {
    projectDir = file("versions/$version")
    buildFileName = "../../build.gradle.kts"
  }
}

rootProject.buildFileName = "root.gradle.kts"
