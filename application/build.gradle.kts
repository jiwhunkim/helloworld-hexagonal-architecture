plugins {
    id("buildlogic.spring-boot-application-conventions")
    id("buildlogic.kotest-conventions")
    id("buildlogic.kotest-spring-conventions")
    id("buildlogic.mockk-conventions")
    id("buildlogic.testcontainers-conventions")
}

dependencies {
    implementation(libs.spring.boot.starter)
    implementation("jakarta.persistence:jakarta.persistence-api")
    implementation("org.springframework.data:spring-data-commons")
    implementation(project(":domain"))
}
