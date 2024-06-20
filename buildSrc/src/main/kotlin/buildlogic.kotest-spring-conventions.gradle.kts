val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    kotlin("jvm")
}

dependencies {
    versionCatalog.findLibrary("kotest.extensions.spring").ifPresent {
        implementation(it)
    }
}
