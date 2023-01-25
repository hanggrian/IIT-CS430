import com.vanniktech.maven.publish.JavaLibrary
import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.SonatypeHost

plugins {
    `java-library`
    checkstyle
    jacoco
    alias(libs.plugins.maven.publish)
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.S01)
    signAllPublications()
    pom(::configurePom)
    configure(JavaLibrary(JavadocJar.Javadoc(), true))
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
