plugins {
    kotlin("jvm")
}
dependencies {
    implementation(platform("io.micrometer:micrometer-tracing-bom:latest.release"))
    implementation("io.micrometer:micrometer-tracing-bridge-otel")
    implementation("io.micrometer:micrometer-tracing")
}
