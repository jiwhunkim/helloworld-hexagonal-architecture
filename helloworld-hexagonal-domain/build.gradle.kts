plugins {
    kotlin("plugin.jpa")
}

val jar: Jar by tasks
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
    implementation(Libs.SpringBoot.starterDataJpa) {
        exclude(module = "hibernate-core")
    }

    implementation(Libs.SpringBoot.starterDataRedis)

    implementation("javax.annotation:javax.annotation-api:1.3.2")
    kapt("javax.annotation:javax.annotation-api:1.3.2")

    implementation(Libs.SpringBoot.starterJdbc)
    implementation("org.hibernate:hibernate-core")

    implementation("com.querydsl:querydsl-jpa")
    kapt(group = "com.querydsl", name = "querydsl-apt", classifier = "jpa")

    testImplementation(Libs.Testcontainer.mysql)
    testImplementation(Libs.Testcontainer.mariadb)

    implementation(project(":helloworld-hexagonal-domain-redis"))
    testImplementation(testFixtures(project(":helloworld-hexagonal-domain-redis")))
    implementation(project(":helloworld-hexagonal-domain-rds"))
    testImplementation(testFixtures(project(":helloworld-hexagonal-domain-rds")))
}
