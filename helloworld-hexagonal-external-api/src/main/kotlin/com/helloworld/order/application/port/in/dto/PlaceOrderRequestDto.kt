package com.helloworld.order.application.port.`in`.dto

data class PlaceOrderRequestDto(
    val orderUser: String,
    val orderItems: MutableList<PlaceOrderRequestOrderItemDto>
) {
}
