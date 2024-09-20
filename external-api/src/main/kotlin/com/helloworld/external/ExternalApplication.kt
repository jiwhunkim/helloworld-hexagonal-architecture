package com.helloworld.external

import com.helloworld.config.AppConfig
import com.helloworld.config.mysql.JpaConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(AppConfig::class, JpaConfig::class)
class ExternalApplication

fun main(args: Array<String>) {
    runApplication<ExternalApplication>(*args)
}
