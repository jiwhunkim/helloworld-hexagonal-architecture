import groovy.lang.Closure
import io.swagger.v3.oas.models.servers.Server

plugins {
    id("buildlogic.spring-boot-application-conventions")
    id("buildlogic.spring-cloud-conventions")
    id("buildlogic.kotest-conventions")
    id("buildlogic.kotest-spring-conventions")
    id("buildlogic.mockk-conventions")
    id("buildlogic.testcontainers-conventions")
    id("buildlogic.mapstruct-conventions")
    id("buildlogic.restdocs-conventions")
    id("buildlogic.micrometer-prometheus-conventions")
    id("buildlogic.micrometer-tracing-conventions")
}

dependencies {
    implementation(project(":data:redis"))
    implementation(project(":data:mysql"))
    implementation(project(":domain"))
    implementation(project(":application"))

    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.actuator)
    implementation(libs.spring.cloud.starter.openfeign)
    implementation(libs.springdoc.openapi.starter.webmvc.ui)
    implementation(libs.logstash.logback.encoder)

    intTestImplementation(testFixtures(project(":data:mysql")))
}

configure<com.epages.restdocs.apispec.gradle.OpenApi3Extension> {
    val local =
        closureOf<Server> {
            this.apply {
                url = "http://localhost:8080"
                description = "local"
            }
        } as Closure<Server>

    setServers(listOf(local))
    title = "external"
    description = "Spring REST Docs"
    version = "${project.version}"
    format = "yaml"
}
