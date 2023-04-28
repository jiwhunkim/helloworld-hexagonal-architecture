package com.helloworld.order.application.port.inside.dto

data class PlaceOrderRequestDto(
    val orderUser: String,
    val orderItems: MutableList<PlaceOrderRequestOrderItemDto>
)
