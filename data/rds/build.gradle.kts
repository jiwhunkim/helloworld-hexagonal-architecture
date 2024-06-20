plugins {
    id("buildlogic.spring-boot-application-conventions")
    id("buildlogic.test-fixtures-conventions")
    id("buildlogic.mapstruct-conventions")
    id("buildlogic.kotest-conventions")
    id("buildlogic.kotest-spring-conventions")
    id("buildlogic.mockk-conventions")
    id("buildlogic.testcontainers-conventions")
    alias(libs.plugins.flywaydb.flyway)
    alias(libs.plugins.kotlin.jpa)
}

val jar: Jar by tasks
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
    implementation(libs.spring.boot.starter.jpa) {
        exclude(group = "org.hibernate.orm", module = "hibernate-core")
    }

    implementation(libs.jakarta.annotation.api)
    kapt("jakarta.persistence:jakarta.persistence-api")

    implementation(libs.spring.boot.starter.jdbc) {
        exclude(group = "org.mariadb.jdbc", module = "mariadb-java-client")
    }
    implementation("org.hibernate.orm:hibernate-core")

    implementation(group = "com.querydsl", name = "querydsl-jpa", classifier = "jakarta")
    kapt(group = "com.querydsl", name = "querydsl-apt", classifier = "jakarta")

    implementation(libs.flywaydb.core)
    implementation(libs.flywaydb.mysql)
    implementation(project(":domain"))
    implementation(project(":application"))

    testFixturesImplementation(testFixtures(project(":domain")))
    testFixturesImplementation(libs.kotest.runner.junit5)
    testFixturesImplementation(libs.fixture)
    testFixturesImplementation(libs.fixture.kotest)
    testFixturesImplementation(libs.testcontainers.mariadb)
    // jackson object mapper
    testImplementation(libs.spring.boot.starter.web)
    testImplementation(libs.testcontainers.mariadb)

    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
    runtimeOnly("com.h2database:h2")
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.MappedSuperclass")
}
