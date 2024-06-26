package com.helloworld

import io.kotest.core.config.AbstractProjectConfig
import org.testcontainers.containers.GenericContainer

object RedisContainer : AbstractProjectConfig() {
    val redisContainer =
        GenericContainer<Nothing>("redis:latest").apply {
            withExposedPorts(6379)
        }

    override suspend fun beforeProject() {
        super.beforeProject()
        if (redisContainer.isRunning) {
            val redisHost = redisContainer.host
            val redisPort = redisContainer.getMappedPort(6379)
            System.setProperty("spring.redis.host", redisHost)
            System.setProperty("spring.redis.port", redisPort.toString())
        }
    }
}
