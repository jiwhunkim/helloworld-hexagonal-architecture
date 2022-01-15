package com.helloworld.order.adapter.`in`.web

import com.helloworld.order.application.port.`in`.dto.GetOrderResponseDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


interface OrderControllerInterface {
    @GetMapping("/orders/{id}")
    fun getOrder(@PathVariable id: Long): GetOrderResponseDto
}
