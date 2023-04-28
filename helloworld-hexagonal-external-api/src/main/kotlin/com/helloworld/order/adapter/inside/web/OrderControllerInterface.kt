package com.helloworld.order.adapter.inside.web

import com.helloworld.order.application.port.inside.dto.GetOrderResponseDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

interface OrderControllerInterface {
    @GetMapping("/orders/{id}")
    fun getOrder(@PathVariable id: Long): GetOrderResponseDto
}
