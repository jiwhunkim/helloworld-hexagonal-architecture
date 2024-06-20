val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    kotlin("jvm")
}

dependencies {
    versionCatalog.findLibrary("kotest.runner.junit5").ifPresent {
        testImplementation(it)
    }
    versionCatalog.findLibrary("kotest.assertions.core").ifPresent {
        testImplementation(it)
    }
    versionCatalog.findLibrary("kotest.framework.datatest").ifPresent {
        testImplementation(it)
    }
    versionCatalog.findLibrary("fixture").ifPresent {
        testImplementation(it)
    }
    versionCatalog.findLibrary("fixture-kotest").ifPresent {
        testImplementation(it)
    }
}
