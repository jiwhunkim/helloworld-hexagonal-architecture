package com.helloworld.external.health.adapter.inside.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

private const val HELATH_OK = "OK"

@RestController
class HealthController {
    @GetMapping("/health")
    fun health(): String = HELATH_OK
}
