val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    kotlin("jvm")
}

dependencies {
    versionCatalog.findLibrary("kotlin.logging.jvm").ifPresent {
        implementation(it)
    }
}
