package com.helloworld

import io.kotest.core.config.AbstractProjectConfig
import org.testcontainers.containers.MariaDBContainer
import org.testcontainers.utility.DockerImageName

object MysqlContainer {
    val container =
        MariaDBContainer<Nothing>(
            DockerImageName
                .parse("mariadb:latest"),
        ).apply {
            withUrlParam("characterEncoding", "UTF-8")
            withUrlParam("useUnicode", "true")
            withUrlParam("sslMode", "DISABLED")
            withUrlParam("useSSL", "false")
            withUrlParam("serverTimezone", "Asia/Seoul")
            withUsername("root")
            withPassword("")
            withCommand(
                "--skip-character-set-client-handshake --character-set-server=utf8mb4 --collation-server=utf8mb4_general_ci --character-set-filesystem=utf8mb4",
            )
        }
}
