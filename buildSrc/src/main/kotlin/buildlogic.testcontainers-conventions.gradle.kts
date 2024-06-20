val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    kotlin("jvm")
}

dependencies {
    versionCatalog.findLibrary("testcontainers").ifPresent {
        implementation(platform(it))
    }
    versionCatalog.findLibrary("kotest.extensions.testcontainers").ifPresent {
        implementation(it)
    }
}
