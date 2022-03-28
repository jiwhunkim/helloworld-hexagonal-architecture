import org.gradle.api.file.DuplicatesStrategy.INCLUDE
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath(Libs.snakeYaml)
        classpath(Libs.kotlinAllopen)
    }
}

extra["kotlin-coroutines.version"] = "1.6.0"

plugins {
    kotlin("jvm") version Libs.Versions.kotlin
    kotlin("kapt") version Libs.Versions.kotlin
    kotlin("plugin.spring") version Libs.Versions.kotlin
    kotlin("plugin.jpa") version Libs.Versions.kotlin

    id("org.jlleitschuh.gradle.ktlint") version Libs.Versions.ktlint
    id("org.jlleitschuh.gradle.ktlint-idea") version Libs.Versions.ktlint
    id("org.springframework.boot") version Libs.Versions.spring_boot
    id("io.spring.dependency-management") version Libs.Versions.spring_dependency_management
    id("org.jetbrains.kotlin.plugin.allopen") version Libs.Versions.allopen
    id("org.sonarqube") version Libs.Versions.sonarqube
    id("org.jetbrains.kotlin.plugin.lombok") version Libs.Versions.kotlin
    id("io.freefair.lombok") version "6.3.0"
    jacoco
}

sonarqube {
    properties {
        property("sonar.projectKey", System.getenv()["SONAR_PROJECT_KEY"] ?: "helloworld-hexagonal")
        property("sonar.host.url", System.getenv()["SONAR_HOST_URL"] ?: "http://localhost:9000")
    }
}

tasks.jar {
    enabled = true
}

tasks.bootJar {
    enabled = false
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("jacoco")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    sonarqube {
        properties {
            property("sonar.coverage.jacoco.xmlReportPaths", "${project.buildDir}/reports/jacoco/test/jacocoTestReport.xml")
        }
    }

    configure<JacocoPluginExtension> {
        toolVersion = Libs.Versions.jacoco
    }

    tasks.withType<JacocoReport> {
        reports {
            html.isEnabled = true
            xml.isEnabled = true
            csv.isEnabled = false
        }
    }

    tasks.withType<JacocoCoverageVerification> {
        violationRules {
            rule {
                element = "BUNDLE"

                limit {
                    counter = "BRANCH"
                    value = "COVEREDRATIO"
                    minimum = "0.0".toBigDecimal()
                }
            }
        }
    }
}

val kotlinModules = subprojects
configure(kotlinModules) {
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "kotlin-lombok")

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "11"
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }

    tasks.check {
        dependsOn(":ktlintCheck")
    }

    kapt {
        keepJavacAnnotationProcessors = true
    }

    kotlinLombok {
        lombokConfigurationFile(file("${project.rootDir}/lombok.config"))
    }
}

val coreModules = listOf(
    project("helloworld-hexagonal-domain-rds"),
    project("helloworld-hexagonal-domain-redis"),
    project("helloworld-hexagonal-domain"),
    project("helloworld-hexagonal-external-api")
)

val springModules = listOf(
    project("helloworld-hexagonal-domain-rds"),
    project("helloworld-hexagonal-domain-redis"),
    project("helloworld-hexagonal-domain"),
    project("helloworld-hexagonal-external-api"),
    project("client:coupon")
)

val rdsModules = listOf(
    project("helloworld-hexagonal-domain-rds"),
    project("helloworld-hexagonal-domain")
)

val testfixtureModules = listOf(
    project("helloworld-hexagonal-domain-rds"),
    project("helloworld-hexagonal-domain-redis"),
    project("helloworld-hexagonal-domain"),
    project("helloworld-hexagonal-external-api")
)

val mapstructModules = listOf(
    project("helloworld-hexagonal-domain"),
    project("helloworld-hexagonal-external-api")
)

val flywayModules = listOf(
    project("helloworld-hexagonal-domain-rds"),
    project("helloworld-hexagonal-domain"),
    project("helloworld-hexagonal-external-api")

)
configure(coreModules) {
    dependencies {
        implementation("org.projectlombok:lombok:1.18.22")
        annotationProcessor("org.projectlombok:lombok:1.18.22")
        implementation("com.google.code.findbugs:jsr305:3.0.2")

        implementation(platform(Libs.Boms.kotlinBom))

        implementation(platform(Libs.Boms.testContainerBom))
        testImplementation(Libs.Kotest.runnerJunit5)
        testImplementation(Libs.Kotest.assertionsCore)
        testImplementation(Libs.Kotest.extensionsSpring)
        testImplementation(Libs.Kotest.extensionsTestcontainers)
        testImplementation(Libs.fixture)
        testImplementation(Libs.mockk)
        testImplementation(Libs.springMockk)

        testImplementation(Libs.Testcontainer.junitJupiter)
        testImplementation(Libs.Testcontainer.mysql)
        testImplementation(Libs.Testcontainer.mariadb)
        testImplementation(Libs.Testcontainer.kafka)
    }

    tasks.withType<ProcessResources> {
        // 동일한 파일(main/resources/application.yaml, intTest/resources/application.yaml)이 있어서, 리소스 복사할 때 충돌 회피
        // Execution failed for task ':web:processIntTestResources'.
        // > Entry application.yaml is a duplicate but no duplicate handling strategy has been set.
        duplicatesStrategy = INCLUDE
    }

    sourceSets {
        create("intTest") {
            compileClasspath += sourceSets.main.get().output + sourceSets.test.get().output
            runtimeClasspath += sourceSets.main.get().output + sourceSets.test.get().output

            resources.srcDir(file("src/intTest/resources"))
        }
    }

    val intTestImplementation: Configuration by configurations.getting {
        extendsFrom(configurations.implementation.get(), configurations.testImplementation.get())
    }

    configurations["intTestImplementation"].extendsFrom(configurations.testImplementation.get())
    configurations["intTestRuntimeOnly"].extendsFrom(configurations.runtimeOnly.get())

    val intTest = task<Test>("intTest") {
        description = "Runs integration tests."
        group = "verification"

        testClassesDirs = sourceSets["intTest"].output.classesDirs
        classpath = sourceSets["intTest"].runtimeClasspath
        shouldRunAfter("test")
    }

    tasks.check { dependsOn(intTest) }

    tasks.jacocoTestReport {
        var qDomains = ('A'..'Z').map { "**/Q$it*" }

        classDirectories.setFrom(
            files(
                classDirectories.files.map {
                    fileTree(it) {
                        exclude(
                            qDomains
                        )
                    }
                }
            )
        )
        executionData.setFrom(fileTree(buildDir).include("/jacoco/*.exec"))
        shouldRunAfter(tasks.test, tasks.findByName("intTest")) // tests are required to run before generating the report
    }

// kotlin-allopen plugin configuration
// ref: https://kotlinlang.org/docs/all-open-plugin.html
// allOpen {
//   annotation("com.my.Annotation")
//   annotations("com.another.Annotation", "com.third.Annotation")
// }
}
configure(springModules) {
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "kotlin-spring") // instead of "kotlin-allopen"
    apply(plugin = "org.springframework.boot")

    dependencies {
        implementation(Libs.jacksonModuleKotlin)
        kapt(Libs.SpringBoot.configurationProcessor)
        testImplementation(Libs.SpringBoot.starterTest)
    }
}
configure(testfixtureModules) {
    apply {
        plugin("java-test-fixtures")
    }

    dependencies {
        "testFixturesImplementation"(Libs.SpringBoot.starterTest)
        "testFixturesImplementation"(Libs.Kotest.runnerJunit5) // for kotest core jvm assertions
        "testFixturesImplementation"(Libs.Kotest.assertionsCore) // for kotest property test
        "testFixturesImplementation"(Libs.Kotest.extensionsSpring)
        "testFixturesImplementation"(Libs.Kotest.extensionsTestcontainers)
        "testFixturesImplementation"(Libs.Testcontainer.junitJupiter)
        "testFixturesImplementation"(Libs.Testcontainer.mysql)
        "testFixturesImplementation"(Libs.Testcontainer.mariadb)
    }
}

configure(mapstructModules) {
    dependencies {
        implementation(Libs.MapStruct.mapstruct)
        implementation(Libs.MapStruct.mapstructJdk8)
        kapt(Libs.MapStruct.mapstructProcessor)
    }
}

configure(flywayModules) {
    dependencies {
        implementation(Libs.flywayCore)
        implementation(Libs.flywayMariaDb)
    }
}

jacoco {
    toolVersion = Libs.Versions.jacoco
}

task<JacocoReport>("jacocoRootReport") {
    dependsOn(subprojects.map { it.tasks.withType<JacocoReport>() })
    sourceDirectories.setFrom(subprojects.map { it.tasks.findByName("jacocoTestReport")!!.property("sourceDirectories") })
    classDirectories.setFrom(subprojects.map { it.tasks.findByName("jacocoTestReport")!!.property("classDirectories") })
    executionData.setFrom(
        project.fileTree(".") {
            include("**/build/jacoco/**.exec")
        }
    )
    onlyIf {
        true
    }
    reports {
        xml.isEnabled = true
        html.isEnabled = true
    }
}
