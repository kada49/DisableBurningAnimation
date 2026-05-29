plugins {
    id("gg.essential.loom") version "1.15.50" apply false
    id("gg.essential.multi-version.root")
}

preprocess {
    strictExtraMappings.set(true)

    val fabric12000 = createNode("fabric-1.20", 12000, "yarn")
    val fabric12001 = createNode("fabric-1.20.1", 12001, "yarn")
    val fabric12002 = createNode("fabric-1.20.2", 12002, "yarn")
    val fabric12003 = createNode("fabric-1.20.3", 12003, "yarn")
    val fabric12004 = createNode("fabric-1.20.4", 12004, "yarn")
    val fabric12005 = createNode("fabric-1.20.5", 12005, "yarn")
    val fabric12006 = createNode("fabric-1.20.6", 12006, "yarn")

    val fabric12100 = createNode("fabric-1.21", 12100, "yarn")
    val fabric12101 = createNode("fabric-1.21.1", 12101, "yarn")
    val fabric12102 = createNode("fabric-1.21.2", 12102, "yarn")
    val fabric12103 = createNode("fabric-1.21.3", 12103, "yarn")
    val fabric12104 = createNode("fabric-1.21.4", 12104, "yarn")
    val fabric12105 = createNode("fabric-1.21.5", 12105, "yarn")
    val fabric12106 = createNode("fabric-1.21.6", 12106, "yarn")
    val fabric12107 = createNode("fabric-1.21.7", 12107, "yarn")
    val fabric12108 = createNode("fabric-1.21.8", 12108, "yarn")
    val fabric12109 = createNode("fabric-1.21.9", 12109, "yarn")
    val fabric12110 = createNode("fabric-1.21.10", 12110, "yarn")
    val fabric12111 = createNode("fabric-1.21.11", 12111, "yarn")

    val fabric260100 = createNode("fabric-26.1", 260100, "yarn")
    val fabric260101 = createNode("fabric-26.1.1", 260101, "yarn")
    val fabric260102 = createNode("fabric-26.1.2", 260102, "yarn")


    // fabric12001.link(fabric12000)
    // fabric12002.link(fabric12001)
    // fabric12003.link(fabric12002)
    // fabric12004.link(fabric12003)
    // fabric12005.link(fabric12004)
    // fabric12006.link(fabric12005)

    // fabric12100.link(fabric12000)
    // fabric12101.link(fabric12100)
    // fabric12102.link(fabric12101)
    // fabric12103.link(fabric12102)
    // fabric12104.link(fabric12103)
    // fabric12105.link(fabric12104)
    // fabric12106.link(fabric12105)
    // fabric12107.link(fabric12106)
    // fabric12108.link(fabric12107)
    // fabric12109.link(fabric12108)
    // fabric12110.link(fabric12109)
    // fabric12111.link(fabric12110)

    // fabric260100.link(fabric12100)
    // fabric260101.link(fabric260100)
    // fabric260102.link(fabric260101)


    fabric12000.link(fabric12001)
    fabric12001.link(fabric12002)
    fabric12002.link(fabric12003)
    fabric12003.link(fabric12004)
    fabric12004.link(fabric12005)
    fabric12005.link(fabric12006)

    fabric12006.link(fabric12100)
    fabric12100.link(fabric12101)
    fabric12101.link(fabric12102)
    fabric12102.link(fabric12103)
    fabric12103.link(fabric12104)
    fabric12104.link(fabric12105)
    fabric12105.link(fabric12106)
    fabric12106.link(fabric12107)
    fabric12107.link(fabric12108)
    fabric12108.link(fabric12109)
    fabric12109.link(fabric12110)
    fabric12110.link(fabric12111)

    fabric12111.link(fabric260100)
    fabric260100.link(fabric260101)
    fabric260101.link(fabric260102)
}
