package com.helloworld.order.application.service.dto

data class OrderDto(
    val id: Long,
    val orderUser: String,
    val orderItems: MutableList<OrderItemDto>
)
