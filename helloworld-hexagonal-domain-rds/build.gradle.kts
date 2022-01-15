plugins {
  id("org.flywaydb.flyway") version "8.2.3"
  kotlin("plugin.jpa")
  id("java-test-fixtures")
}

val jar: Jar by tasks
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
    implementation(Libs.SpringBoot.starterDataJpa) {
        exclude(module = "hibernate-core")
    }

    implementation("javax.annotation:javax.annotation-api:1.3.2")
    kapt("jakarta.persistence:jakarta.persistence-api")
    kapt("javax.annotation:javax.annotation-api:1.3.2")

    implementation(Libs.SpringBoot.starterJdbc)
    implementation("org.hibernate:hibernate-core")

    implementation("com.querydsl:querydsl-jpa")
    kapt(group = "com.querydsl", name = "querydsl-apt", classifier = "jpa")

    runtimeOnly(Libs.mariadbJavaClient)
}

allOpen {
  annotation("javax.persistence.Entity")
  annotation("javax.persistence.Embeddable")
  annotation("javax.persistence.MappedSuperclass")
}
