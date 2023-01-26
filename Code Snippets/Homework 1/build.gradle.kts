plugins {
    java
    checkstyle
}

checkstyle {
    toolVersion = libs.versions.checkstyle.get()
    configFile = projectDir.resolve("rulebook_checks.xml")
}

dependencies {
    checkstyle(libs.checkstyle)
    checkstyle(libs.rulebook.checkstyle)
    testImplementation(libs.truth)
}
