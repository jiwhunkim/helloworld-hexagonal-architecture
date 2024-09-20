package com.helloworld

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean

@TestConfiguration(proxyBeanMethods = false)
class MysqlContainerConfig {
    @Bean
    @ServiceConnection
    fun mysqlContainer() = MysqlContainer.container
}
