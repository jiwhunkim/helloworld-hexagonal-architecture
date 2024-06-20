plugins {
    id("buildlogic.spring-boot-application-conventions")
    id("buildlogic.spring-cloud-conventions")
}

val jar: Jar by tasks
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
    implementation(libs.springdoc.openapi.starter.webmvc.ui)
    implementation(libs.spring.cloud.starter.openfeign)
    implementation("io.github.openfeign:feign-hc5")
    implementation(libs.google.jsr305)
}
