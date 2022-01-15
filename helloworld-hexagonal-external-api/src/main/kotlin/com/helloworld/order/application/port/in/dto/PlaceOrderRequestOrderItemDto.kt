package com.helloworld.order.application.port.`in`.dto

import java.math.BigDecimal

data class PlaceOrderRequestOrderItemDto(
    val productId: Long,
    val productName: String,
    val quantity: Int,
    val price: BigDecimal
) {
}
