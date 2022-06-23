package com.helloworld.order.application.port.inside

import com.helloworld.order.application.service.dto.OrderDto
import com.helloworld.order.domain.PlaceOrder

interface OrderApplicationPort {
    fun getOrderDto(id: Long): OrderDto

    fun placeOrderDto(placeOrder: PlaceOrder): OrderDto
}
