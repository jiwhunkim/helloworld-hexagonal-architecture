package com.helloworld.order.application.port.`in`

import com.helloworld.order.application.port.`in`.dto.GetOrderResponseDto
import com.helloworld.order.application.port.`in`.dto.PlaceOrderRequestDto

interface OrderApplicationServicePort {
    fun getOrder(id: Long): GetOrderResponseDto

    fun placeOrder(placeOrderRequestDto: PlaceOrderRequestDto): GetOrderResponseDto
}
