val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    implementation("org.mapstruct:mapstruct-jdk8:1.5.5.Final")
    kapt("org.mapstruct:mapstruct-processor:1.5.5.Final")
}
