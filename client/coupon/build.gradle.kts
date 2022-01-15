plugins {
}

dependencyManagement {
    imports {
        mavenBom(Libs.Boms.springCloudBom)
    }
}

dependencies {
    implementation(Libs.SpringBoot.starterWeb)
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
}

val jar: Jar by tasks
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = false
jar.enabled = true
