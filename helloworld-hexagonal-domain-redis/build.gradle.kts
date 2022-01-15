val jar: Jar by tasks
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
  implementation(Libs.SpringBoot.starterDataRedis)
  implementation("org.springframework.integration:spring-integration-redis")
  implementation("io.lettuce:lettuce-core")

  implementation("org.apache.commons:commons-lang3")
  implementation("org.apache.commons:commons-pool2")
}
