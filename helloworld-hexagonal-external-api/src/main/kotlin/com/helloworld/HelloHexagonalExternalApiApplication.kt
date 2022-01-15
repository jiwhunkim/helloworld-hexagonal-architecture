package com.helloworld

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HelloHexagonalExternalApiApplication

fun main(args: Array<String>) {
    runApplication<HelloHexagonalExternalApiApplication>(*args)
}
