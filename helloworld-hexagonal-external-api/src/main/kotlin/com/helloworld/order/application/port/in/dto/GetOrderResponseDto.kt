package com.helloworld.order.application.port.`in`.dto

import com.helloworld.order.application.service.dto.OrderDto

data class GetOrderResponseDto(
    val order: OrderDto
) {
}
