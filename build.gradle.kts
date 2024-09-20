import io.gitlab.arturbosch.detekt.Detekt
import net.razvan.JacocoToCoberturaPlugin
import net.razvan.JacocoToCoberturaTask

apply(from = "$rootDir/gradle/install-git-hooks.gradle.kts")

plugins {
    id("org.jetbrains.kotlinx.kover")
    id("org.sonarqube") version libs.versions.sonarqube.get()
    id("io.gitlab.arturbosch.detekt") version libs.versions.detekt.get()
    id("org.jlleitschuh.gradle.ktlint") version libs.versions.ktlint.get()
    id("net.razvan.jacoco-to-cobertura") version libs.versions.jacoco.to.cobertura.get()
}

ext["mariadb.version"] = "2.7.11"

allprojects {
    repositories {
        mavenCentral()
    }

    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "io.gitlab.arturbosch.detekt")

    dependencies {
        detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.6")
    }

    detekt {
        buildUponDefaultConfig = true // preconfigure defaults
        allRules = false // activate all available (even unstable) rules.
        config.setFrom(
            file("$rootDir/config/detekt.yml"),
        ) // point to your custom config defining rules to run, overwriting default behavior
        baseline = file("$rootDir/config/baseline.xml") // a way of suppressing issues before introducing detekt
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

sonarqube {
    properties {
        property("sonar.host.url", System.getenv()["SONAR_HOST_URL"] ?: "http://localhost:9000")
        property("sonar.sourceEncoding", "UTF-8")
        property("sonar.exclusions", "**/*Test*.*, **/*Mapper*.*, **/Q*.java, **/*Repository.kt, **/*Interceptor.kt, **entity/*, **/*Entity.*, **/*Constants.*, **/*Config.*, **Application.kt, **/*Request*.*, **/*Response*.*")
        property("sonar.cpd.exclusions", "**/*Config.kt,**/*Configuration.kt")
        property("sonar.tests", "src/intTest/kotlin,src/test/kotlin")
        property("sonar.test.inclusions", "**/*Test.kt,**/*Spec.kt,**/*TestConfig.kt")
        property("sonar.coverage.exclusions", "**/*Test*.*, **/*Mapper*.*, **/Q*.java, **/*Repository.kt, **/*Interceptor.kt, **entity/*,**/*Entity.*, **/*Constants.*, **/*Config.*, **Application.kt, **/*Request*.*, **/*Response*.*")
        property("sonar.qualitygate.wait", "true")
        property("sonar.projectKey", System.getenv()["SONAR_PROJECT_KEY"] ?: "helloworld-hexagonal")
        property("sonar.coverage.jacoco.xmlReportPaths", "${project.buildDir}/reports/kover/report.xml")
    }
}

dependencies {
    kover(project("data:mysql"))
    kover(project("data:redis"))
    kover(project(":domain"))
    kover(project(":external-api"))
}

kover {
    reports {
        filters {
            // exclusions for reports
            excludes {
                // excludes class by fully-qualified JVM class name, wildcards '*' and '?' are available
                classes(
                    "*Kt",
                    "*Spec",
                    "*Test",
                    "*Mapper*",
                    "*Config",
                    "*Repository",
                    "*Interceptor",
                    "*entity*",
                    "*Entity*",
                    "*Constants",
                    "*Application",
                    "*Request",
                    "*Response",
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

tasks.named<JacocoToCoberturaTask>(JacocoToCoberturaPlugin.TASK_NAME) {
    inputFile.set(file("build/reports/kover/report.xml"))
    outputFile.set(file("build/reports/kover/cobertura.xml"))
}

tasks.register("koverPrintMergedXmlCoverage") {
    group = "verification"
    description = "print total code coverage"

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
