import io.gitlab.arturbosch.detekt.Detekt
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

plugins {
    kotlin("jvm") version Libs.Versions.kotlin
    kotlin("kapt") version Libs.Versions.kotlin
    kotlin("plugin.spring") version Libs.Versions.kotlin
    kotlin("plugin.jpa") version Libs.Versions.kotlin

    id("io.gitlab.arturbosch.detekt") version Libs.Versions.detekt
    id("org.jlleitschuh.gradle.ktlint") version Libs.Versions.ktlint
    id("org.jlleitschuh.gradle.ktlint-idea") version Libs.Versions.ktlint
    id("org.springframework.boot") version Libs.Versions.springBoot
    id("io.spring.dependency-management") version Libs.Versions.springDependencyManagement
    id("org.jetbrains.kotlin.plugin.allopen") version Libs.Versions.allopen
    id("org.jetbrains.kotlinx.kover") version Libs.Versions.kover
    id("org.sonarqube") version Libs.Versions.sonarqube
    id("org.jetbrains.kotlin.plugin.lombok") version Libs.Versions.kotlin
    id("io.freefair.lombok") version Libs.Versions.freefairLombok
    id("net.razvan.jacoco-to-cobertura") version "1.1.0"
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

    apply(plugin = "io.gitlab.arturbosch.detekt")

    detekt {
        autoCorrect = true
        buildUponDefaultConfig = true // preconfigure defaults
        allRules = false // activate all available (even unstable) rules.
        config = files("$rootDir/config/detekt.yml") // point to your custom config defining rules to run, overwriting default behavior
        baseline = file("$rootDir/config/baseline.xml") // a way of suppressing issues before introducing detekt
    }

    dependencies {
        detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.22.0")
    }

    tasks.withType<Detekt>().configureEach {
        reports {
            html.required.set(true) // observe findings in your browser with structure and code snippets
            xml.required.set(true) // checkstyle like format mainly for integrations like Jenkins
            txt.required.set(true) // similar to the console output, contains issue signature to manually edit baseline files
            sarif.required.set(true) // standardized SARIF format (https://sarifweb.azurewebsites.net/) to support integrations with GitHub Code Scanning
            md.required.set(true) // simple Markdown format
        }
    }
}

subprojects {
    tasks.withType<Test> {
        jvmArgs("--add-opens", "java.base/java.time=ALL-UNNAMED")
    }

    sonarqube {
        properties {
            property("sonar.coverage.jacoco.xmlReportPaths", "${project.buildDir}/reports/kover/report.xml")
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
            jvmTarget = "17"
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
    project("helloworld-hexagonal-domain")

)

configure(coreModules) {
    dependencies {
        implementation("org.projectlombok:lombok:${Libs.Versions.lombok}")
        annotationProcessor("org.projectlombok:lombok:${Libs.Versions.lombok}")
        implementation("com.google.code.findbugs:jsr305:3.0.2")

        implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")

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

    tasks.named("check") {
        dependsOn(testing.suites.named("intTest"))
    }
}
configure(springModules) {
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "kotlin-spring") // instead of "kotlin-allopen"
    apply(plugin = "org.springframework.boot")
    apply(plugin = "org.jetbrains.kotlinx.kover")

    dependencies {
        implementation(Libs.jacksonModuleKotlin)
        kapt(Libs.SpringBoot.springBootConfigurationProcessor)
        runtimeOnly(Libs.SpringBoot.springBootDevtools)
        testImplementation(Libs.SpringBoot.starterTest)
    }

    ext["mariadb.version"] = Libs.Versions.mariadb
}
configure(testfixtureModules) {
    apply(plugin = "java-test-fixtures")

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

configure(flywayModules) {
    dependencies {
        implementation(Libs.flywayCore)
        implementation(Libs.flywayMariaDb)
    }
}

configure(mapstructModules) {
    dependencies {
        implementation(Libs.MapStruct.mapstruct)
        implementation(Libs.MapStruct.mapstructJdk8)
        kapt(Libs.MapStruct.mapstructProcessor)
    }
}

sonarqube {
    properties {
        property("sonar.host.url", System.getenv()["SONAR_HOST_URL"] ?: "http://localhost:9000")
        property("sonar.sourceEncoding", "UTF-8")
        property("sonar.exclusions", "**/*Test*.*,**/Q*.java,**/*Repository.kt,**/*Interceptor.kt,**entity/*,**/*Entity.*,**/*Constants.*")
        property("sonar.cpd.exclusions", "**/*Config.kt,**/*Configuration.kt")
        property("sonar.tests", "src/intTest/kotlin,src/test/kotlin")
        property("sonar.test.inclusions", "**/*Test.kt,**/*TestConfig.kt")
        property("sonar.coverage.exclusions", "**/*Test*.*,**/Q*.java,**/*Repository.kt,**/*Interceptor.kt,**entity/*,**/*Entity.*,**/*Constants.*")
        property("sonar.sourceEncoding", "UTF-8")
        property("sonar.qualitygate.wait", "true")
        property("sonar.projectKey", System.getenv()["SONAR_PROJECT_KEY"] ?: "helloworld-hexagonal")
        property("sonar.coverage.jacoco.xmlReportPaths", "${project.buildDir}/reports/kover/report.xml")
    }
}

dependencies {
    kover(project("helloworld-hexagonal-domain-rds"))
    kover(project("helloworld-hexagonal-domain-redis"))
    kover(project("helloworld-hexagonal-domain"))
    kover(project("helloworld-hexagonal-external-api"))
    kover(project("client:coupon"))
}

koverReport {
    defaults {
        filters {
            // exclusions for reports
            excludes {
                // excludes class by fully-qualified JVM class name, wildcards '*' and '?' are available
                classes(
                    "*Kt",
                    "*Spec",
                    "*Test",
                    "*Config",
                    "*Repository",
                    "*Interceptor",
                    "*entity*",
                    "*Entity*",
                    "*Constants*"
                )
                // excludes all classes located in specified package and it subpackages, wildcards '*' and '?' are available
                // packages("com.another.subpackage")
                // excludes all classes and functions, annotated by specified annotations, wildcards '*' and '?' are available
                annotatedBy("*Generated*")
            }

            // inclusions for reports
            includes {
                // includes class by fully-qualified JVM class name, wildcards '*' and '?' are available
                classes("com.helloworld.*")
                // includes all classes located in specified package and it subpackages
                // packages("com.another.subpackage")
            }
        }
    }
}

jacocoToCobertura {
    inputFile.set(file("build/reports/kover/report.xml"))
    outputFile.set(file("build/reports/kover/cobertura.xml"))
}

tasks.register("koverPrintMergedXmlCoverage") {
    group = "verification"

    val koverMergedXmlReport = tasks.named("koverXmlReport")
    dependsOn(koverMergedXmlReport)
    doLast {
        //language=RegExp
        val regexp = """<counter type="INSTRUCTION" missed="(\d+)" covered="(\d+)"/>""".toRegex()
        koverMergedXmlReport.get().outputs.files.forEach { file ->
            // Read file by lines
            file.useLines { lines ->
                // Last line in file that matches regexp is the total coverage
                lines.last(regexp::containsMatchIn).let { line ->
                    // Found the match
                    regexp.find(line)?.let {
                        val missed = it.groupValues[1].toInt()
                        val covered = it.groupValues[2].toInt()
                        println("Total Code Coverage: ${covered * 100 / (missed + covered)}%")
                    }
                }
            }
        }
    }
}
