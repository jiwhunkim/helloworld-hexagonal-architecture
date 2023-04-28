object Libs {
    object Versions {
        const val kotlin = "1.8.20"
        const val springBoot = "3.0.6"
        const val springCloud = "2022.0.2"
        const val spring_cloud_aws = "2.3.0"
        const val springDependencyManagement = "1.1.0"
        const val ktlint = "11.3.1"
        const val allopen = "1.7.20"
        const val kassava = "2.0.0"
        const val kotest = "5.6.1"
        const val kotestExtensionsSpring = "1.1.3"
        const val kotestExtensionsTestcontainers = "1.3.4"
        const val mockk = "1.13.5"
        const val hibernate_jpa_api = "1.0.2.Final"
        const val querydsl = "4.2.2"
        const val slf4j = "1.7.30"
        const val logbackClassic = "1.2.3"
        const val logstashLogbackEncoder = "6.1"
        const val swagger = "3.0.0"
        const val okhttp = "4.6.0"
        const val awssdk = "2.16.89"
        const val mapstruct = "1.4.2.Final"
        const val sonarqube = "4.0.0.2929"
        const val jacoco = "0.8.8"
        const val testcontainer = "1.18.0"
        const val flyway = "9.16.3"
        const val lombok = "1.18.26"
        const val detekt = "1.22.0"
        const val kover = "0.7.0-Beta"
        const val freefairLombok = "8.0.1"
        const val jacocoToCobertura = "1.1.0"
        const val springopenapi = "2.1.0"
    }

    object Boms {
        const val kotlinBom = "org.jetbrains.kotlin:kotlin-bom:${Versions.kotlin}"
        const val awsSdkBom = "software.amazon.awssdk:bom:${Versions.awssdk}"
        const val springCloudBom = "org.springframework.cloud:spring-cloud-dependencies:${Versions.springCloud}"
        const val testContainerBom = "org.testcontainers:testcontainers-bom:${Versions.testcontainer}"
    }

    object SpringBoot {
        const val starterWeb = "org.springframework.boot:spring-boot-starter-web"
        const val starterWebFlux = "org.springframework.boot:spring-boot-starter-webflux"
        const val starterJdbc = "org.springframework.boot:spring-boot-starter-jdbc"
        const val starterDataJpa = "org.springframework.boot:spring-boot-starter-data-jpa"
        const val starterDataRedis = "org.springframework.boot:spring-boot-starter-data-redis"
        const val starterActuator = "org.springframework.boot:spring-boot-starter-actuator"
        const val starterTest = "org.springframework.boot:spring-boot-starter-test"
        const val starterValidator = "org.springframework.boot:spring-boot-starter-validation"
        const val springBootConfigurationProcessor = "org.springframework.boot:spring-boot-configuration-processor"
        const val springCloudStarterOpenFeign = "org.springframework.cloud:spring-cloud-starter-openfeign"
        const val springRetry = "org.springframework.retry:spring-retry"
        const val springBootDevtools = "org.springframework.boot:spring-boot-devtools"

    }

    object Springdoc {
        const val springdocOpenapiStarterWebmvcUi = "org.springdoc:springdoc-openapi-starter-webmvc-ui:${Versions.springopenapi}"
    }

    object MapStruct {
        const val mapstructJdk8 = "org.mapstruct:mapstruct-jdk8:${Versions.mapstruct}"
        const val mapstruct = "org.mapstruct:mapstruct:${Versions.mapstruct}"
        const val mapstructProcessor = "org.mapstruct:mapstruct-processor:${Versions.mapstruct}"
    }

    object Kotest {
        const val runnerJunit5 = "io.kotest:kotest-runner-junit5:${Versions.kotest}"
        const val assertionsCore = "io.kotest:kotest-assertions-core:${Versions.kotest}"
        const val extensionsSpring = "io.kotest.extensions:kotest-extensions-spring:${Versions.kotestExtensionsSpring}"
        const val extensionsTestcontainers = "io.kotest.extensions:kotest-extensions-testcontainers:${Versions.kotestExtensionsTestcontainers}"
    }

    object Testcontainer {
        const val junitJupiter = "org.testcontainers:junit-jupiter:${Versions.testcontainer}"
        const val mysql = "org.testcontainers:mysql:${Versions.testcontainer}"
        const val mariadb = "org.testcontainers:mariadb:${Versions.testcontainer}"
        const val kafka = "org.testcontainers:kafka:${Versions.testcontainer}"
    }

    const val flywayCore = "org.flywaydb:flyway-core:${Versions.flyway}"
    const val flywayMariaDb = "org.flywaydb:flyway-mysql:${Versions.flyway}"
    const val mariadbJavaClient = "org.mariadb.jdbc:mariadb-java-client"
    const val jacksonModuleKotlin = "com.fasterxml.jackson.module:jackson-module-kotlin"
    const val fixture = "com.appmattus.fixture:fixture:1.2.0"
    const val mockk = "io.mockk:mockk:1.13.5"
    const val springMockk = "com.ninja-squad:springmockk:4.0.2"
    const val snakeYaml = "org.yaml:snakeyaml:1.28"
    const val kotlinAllopen = "org.jetbrains.kotlin:kotlin-allopen:${Versions.allopen}"
    const val httpClient = "org.apache.httpcomponents.client5:httpclient5"
    const val micrometerRegistryPrometheus = "io.micrometer:micrometer-registry-prometheus"
}
