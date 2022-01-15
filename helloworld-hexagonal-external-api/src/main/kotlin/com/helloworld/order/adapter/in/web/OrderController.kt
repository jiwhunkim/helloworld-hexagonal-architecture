package com.helloworld.order.adapter.`in`.web

import com.helloworld.order.application.port.`in`.OrderApplicationServicePort
import com.helloworld.order.application.port.`in`.dto.GetOrderResponseDto
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController(
    private val orderApplicationServicePort: OrderApplicationServicePort
) : OrderControllerInterface {
    override fun getOrder(id: Long): GetOrderResponseDto {
        return orderApplicationServicePort.getOrder(id)
    }
}
