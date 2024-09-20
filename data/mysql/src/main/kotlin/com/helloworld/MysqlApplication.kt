package com.helloworld

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MysqlApplication

fun main(args: Array<String>) {
    runApplication<MysqlApplication>(*args)
}
