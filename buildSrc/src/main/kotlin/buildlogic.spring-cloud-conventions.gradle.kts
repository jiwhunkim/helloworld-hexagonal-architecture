val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    kotlin("jvm")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

dependencies {
    versionCatalog.findLibrary("spring.cloud.bom").ifPresent {
        implementation(platform(it))
    }
}
