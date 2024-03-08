plugins {
    id("gg.essential.loom") version("1.3.12") apply false
}


val modVersion = property("modVersion")!!.toString()
val modName = property("modBaseName")!!.toString()

subprojects {
    group = property("modGroup")!!
    apply(plugin = "gg.essential.loom")
    project.ext.set("modName", modName)
    version = modVersion
}
