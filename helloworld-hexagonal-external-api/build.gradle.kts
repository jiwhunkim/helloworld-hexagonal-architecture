plugins {
}

dependencyManagement {
    imports {
        mavenBom(Libs.Boms.springCloudBom)
    }
}

dependencies {
    implementation(Libs.SpringBoot.starterWeb)
    implementation(Libs.SpringBoot.starterActuator)
    implementation(Libs.micrometerRegistryPrometheus)

//    implementation(Libs.SpringBoot.starterDataJpa) {
//        exclude(module = "hibernate-core")
//    }
//
//    implementation(project(":helloworld-init-domain-redis"))
//    testImplementation(testFixtures(project(":helloworld-init-domain-redis")))
//    implementation(project(":helloworld-init-domain-rds"))
//    testImplementation(testFixtures(project(":helloworld-init-domain-rds")))

    implementation(project(":helloworld-hexagonal-domain"))

    implementation("org.springdoc:springdoc-openapi-ui:1.6.3")
    implementation("org.springdoc:springdoc-openapi-kotlin:1.6.3")
    implementation("org.springdoc:springdoc-openapi-data-rest:1.6.3")
    implementation("org.springdoc:springdoc-openapi-webmvc-core:1.6.3")

//    implementation("org.springframework.boot:spring-boot-starter-security")
//    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

    implementation("net.logstash.logback:logstash-logback-encoder:7.0.1")
}
