pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.fabricmc.net")
        maven("https://maven.architectury.dev/")
        maven("https://maven.minecraftforge.net")
        maven("https://repo.essential.gg/repository/maven-public")
    }
}

rootProject.buildFileName = "build.gradle.kts"
rootProject.name = "DisableBurningAnimation"

listOf(
        "1.8.9-forge",
        "1.20.4-fabric"
).forEach { version ->
    include(":$version")
    project(":$version").apply {
        projectDir = file("versions/$version")
        buildFileName = "./build.gradle.kts"
    }
}
