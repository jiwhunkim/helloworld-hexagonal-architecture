plugins {
    id("buildlogic.test-fixtures-conventions")
    id("buildlogic.spring-boot-application-conventions")
    id("buildlogic.kotest-conventions")
    id("buildlogic.kotest-spring-conventions")
    id("buildlogic.testcontainers-conventions")
}

dependencies {
    implementation(libs.spring.boot.starter.redis)

    implementation("org.springframework.integration:spring-integration-redis")
    implementation("io.lettuce:lettuce-core")

    implementation("org.apache.commons:commons-lang3")
    implementation("org.apache.commons:commons-pool2")

    implementation("io.netty:netty-transport-native-epoll::linux-x86_64")
    implementation("io.netty:netty-transport-native-epoll::linux-aarch_64")

    testFixturesImplementation(testFixtures(project(":domain")))
    testFixturesImplementation(libs.kotest.runner.junit5)
    testFixturesImplementation(libs.testcontainers)
    testFixturesImplementation(libs.fixture)
    testFixturesImplementation(libs.fixture.kotest)
}
