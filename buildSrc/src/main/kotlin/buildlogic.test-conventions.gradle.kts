val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    kotlin("jvm")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    jvmArgs("--add-opens", "java.base/java.time=ALL-UNNAMED", "--add-opens", "java.base/java.lang.reflect=ALL-UNNAMED")
}


testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter()
        }

        register<JvmTestSuite>("intTest") {
            sources {
                java {
                    setSrcDirs(listOf("src/intTest/kotlin"))
                }
                resources {
                    setSrcDirs(listOf("src/intTest/resources"))
                }
            }

            dependencies {
                implementation(project())
            }
        }
    }
}

val intTestImplementation by configurations.getting {
    extendsFrom(configurations.testImplementation.get())
}

tasks.named("check") { dependsOn(testing.suites.named("intTest")) }
