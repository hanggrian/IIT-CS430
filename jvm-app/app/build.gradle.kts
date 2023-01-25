plugins {
    application
    java
    checkstyle
    jacoco
}

application.mainClass.set("$RELEASE_GROUP.app.MyApp")

java.toolchain.languageVersion.set(JavaLanguageVersion.of(libs.versions.jdk.get()))

checkstyle {
    toolVersion = libs.versions.checkstyle.get()
    configFile = projectDir.resolve("rulebook_checks.xml")
}

dependencies {
    checkstyle(libs.checkstyle)
    checkstyle(libs.rulebook.checkstyle)
    testImplementation(libs.truth)
}
