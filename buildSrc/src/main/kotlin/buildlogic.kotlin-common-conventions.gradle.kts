import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.8.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += listOf("-Xjsr305=strict")
    }
}

tasks.withType<ProcessResources> {
    // 동일한 파일(main/resources/application.yaml, intTest/resources/application.yaml)이 있어서, 리소스 복사할 때 충돌 회피
    // Execution failed for task ':web:processIntTestResources'.
    // > Entry application.yaml is a duplicate but no duplicate handling strategy has been set.
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}
