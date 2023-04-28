package com.helloworld.order.adapter.inside.web

import com.helloworld.order.application.port.inside.OrderApplicationServicePort
import com.helloworld.order.application.port.inside.dto.GetOrderResponseDto
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController(
    private val orderApplicationServicePort: OrderApplicationServicePort
) : OrderControllerInterface {
    override fun getOrder(id: Long): GetOrderResponseDto {
        return orderApplicationServicePort.getOrder(id)
    }
}
