val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")
plugins {
    id("buildlogic.kotlin-common-conventions")
    id("buildlogic.kotlin-logging-conventions")
    id("buildlogic.test-conventions")
    id("buildlogic.kover-conventions")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("kapt")
    kotlin("plugin.spring")

}

dependencies {
    kapt("org.springframework.boot:spring-boot-configuration-processor")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    versionCatalog.findLibrary("spring.boot.starter.test").ifPresent {
        testImplementation(it)
    }
    developmentOnly("org.springframework.boot:spring-boot-devtools")
}
