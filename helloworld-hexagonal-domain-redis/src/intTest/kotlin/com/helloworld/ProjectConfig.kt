package com.helloworld

import com.helloworld.RedisContainer.redisContainer
import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.extensions.Extension
import io.kotest.extensions.spring.SpringExtension
import io.kotest.extensions.testcontainers.perProject

class ProjectConfig : AbstractProjectConfig() {
    override fun extensions(): List<Extension> = listOf(SpringExtension, redisContainer.perProject("domain-redis"))
}
