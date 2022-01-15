package com.helloworld.order.application.service.dto

data class OrderDto(
    val id: Long? = null,
    val orderUser: String,
    val orderItems: MutableList<OrderItemDto>
) {
}
