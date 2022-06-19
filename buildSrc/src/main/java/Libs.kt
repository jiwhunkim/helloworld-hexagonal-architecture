object Libs {
  object Versions {
    const val kotlin = "1.7.0"
    const val spring_boot = "2.6.2"
    const val spring_cloud = "2021.0.0"
    const val spring_cloud_aws = "2.3.0"
    const val spring_dependency_management = "1.0.11.RELEASE"
    const val ktlint = "10.1.0"
    const val allopen = "1.5.10"
    const val kassava = "2.0.0"
    const val kotest = "5.3.1"
    const val kotest_extensions = "1.1.0"
    const val mockk = "1.12.2"
    const val querydsl = "4.2.2"
    const val swagger = "3.0.0"
    const val mapstruct = "1.4.2.Final"
    const val sonarqube = "3.3"
    const val jacoco = "0.8.7"
    const val testcontainer = "1.17.2"
    const val flyway = "8.3.0"
  }

  object Boms {
    const val kotlinBom = "org.jetbrains.kotlin:kotlin-bom:${Versions.kotlin}"
    const val springCloudBom = "org.springframework.cloud:spring-cloud-dependencies:${Versions.spring_cloud}"
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
    const val configurationProcessor = "org.springframework.boot:spring-boot-configuration-processor"
    const val springCloudStarterAwsParameterStoreConfig = "org.springframework.cloud:spring-cloud-starter-aws-parameter-store-config:2.2.6.RELEASE"
    const val springCloudStarterOpenFeign = "org.springframework.cloud:spring-cloud-starter-openfeign"
    const val springRetry = "org.springframework.retry:spring-retry"
  }

  object Swagger {
    const val starter = "io.springfox:springfox-boot-starter:${Versions.swagger}"
    const val swagger2 = "io.springfox:springfox-swagger2:${Versions.swagger}"
    const val swaggerUi = "io.springfox:springfox-swagger-ui:${Versions.swagger}"
  }

  object MapStruct {
    const val mapstructJdk8 = "org.mapstruct:mapstruct-jdk8:${Versions.mapstruct}"
    const val mapstruct = "org.mapstruct:mapstruct:${Versions.mapstruct}"
    const val mapstructProcessor = "org.mapstruct:mapstruct-processor:${Versions.mapstruct}"
  }

  object Kotest {
    const val runnerJunit5 = "io.kotest:kotest-runner-junit5:${Versions.kotest}"
    const val assertionsCore = "io.kotest:kotest-assertions-core:${Versions.kotest}"
    const val extensionsSpring = "io.kotest.extensions:kotest-extensions-spring:${Versions.kotest_extensions}"
    const val extensionsTestcontainers = "io.kotest.extensions:kotest-extensions-testcontainers:${Versions.kotest_extensions}"
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
  const val fixture = "com.appmattus.fixture:fixture:1.1.0"
  const val mockk = "io.mockk:mockk:1.11.0"
  const val springMockk = "com.ninja-squad:springmockk:3.1.0"
  const val snakeYaml = "org.yaml:snakeyaml:1.28"
  const val kotlinAllopen = "org.jetbrains.kotlin:kotlin-allopen:${Versions.allopen}"
  const val httpClient = "org.apache.httpcomponents:httpclient"
  const val micrometerRegistryPrometheus = "io.micrometer:micrometer-registry-prometheus"
}
