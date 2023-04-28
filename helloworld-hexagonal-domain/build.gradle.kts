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
    kapt("jakarta.persistence:jakarta.persistence-api")

    implementation(Libs.SpringBoot.starterJdbc)
    implementation("org.hibernate.orm:hibernate-core:6.2.1.Final")


    implementation("com.querydsl:querydsl-jpa")
    kapt(group = "com.querydsl", name = "querydsl-apt", classifier = "jakarta")

    runtimeOnly(Libs.mariadbJavaClient)

    implementation(project(":helloworld-hexagonal-domain-redis"))
    testImplementation(testFixtures(project(":helloworld-hexagonal-domain-redis")))
    implementation(project(":helloworld-hexagonal-domain-rds"))
    testImplementation(testFixtures(project(":helloworld-hexagonal-domain-rds")))
}
