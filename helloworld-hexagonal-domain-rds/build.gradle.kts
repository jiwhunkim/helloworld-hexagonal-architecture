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

    implementation("javax.annotation:javax.annotation-api:1.3.2")
    kapt("jakarta.persistence:jakarta.persistence-api")

    implementation(Libs.SpringBoot.starterJdbc)
    implementation("org.hibernate.orm:hibernate-core:6.2.1.Final")

    implementation("com.querydsl:querydsl-jpa")
    kapt(group = "com.querydsl", name = "querydsl-apt", classifier = "jakarta")

    runtimeOnly(Libs.mariadbJavaClient)
}

allOpen {
  annotation("javax.persistence.Entity")
  annotation("javax.persistence.Embeddable")
  annotation("javax.persistence.MappedSuperclass")
}
