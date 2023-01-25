allprojects {
    group = RELEASE_GROUP
    version = RELEASE_VERSION
}

subprojects {
    plugins.withType<JavaPlugin> {
        the<JavaPluginExtension>().toolchain.languageVersion
            .set(JavaLanguageVersion.of(libs.versions.jdk.get()))
    }
}
