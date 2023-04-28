package com.helloworld.order.application.port.inside

import com.helloworld.order.application.port.inside.dto.GetOrderResponseDto
import com.helloworld.order.application.port.inside.dto.PlaceOrderRequestDto

interface OrderApplicationServicePort {
    fun getOrder(id: Long): GetOrderResponseDto

    fun placeOrder(placeOrderRequestDto: PlaceOrderRequestDto): GetOrderResponseDto
}
