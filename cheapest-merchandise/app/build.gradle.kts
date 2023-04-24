plugins {
    application
    kotlin("jvm") version libs.versions.kotlin
    alias(libs.plugins.packaging)
    alias(libs.plugins.shadow)
}

application.mainClass.set("com.example.App")

packaging {
    appName.set("Cheapest Merchandise")
    modules.set(listOf("javafx.controls"))
    verbose.set(true)
}

kotlin.jvmToolchain(libs.versions.jdk.get().toInt())

dependencies {
    ktlint(libs.ktlint, ::configureKtlint)
    ktlint(libs.rulebook.ktlint)
    implementation(libs.guava.jre)
    implementation(libs.ktfx.controlsfx)
    testImplementation(kotlin("test-junit", libs.versions.kotlin.get()))
    testImplementation(libs.truth)
}
