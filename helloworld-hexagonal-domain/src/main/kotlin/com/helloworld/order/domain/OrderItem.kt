package com.helloworld.order.domain

import java.math.BigDecimal

class OrderItem(
    val id: Long,
    val productId: Long,
    val productName: String,
    val quantity: Int,
    val price: BigDecimal,
    val salePrice: BigDecimal,
    val taxBaseAmount: BigDecimal,
    val taxFreeAmount: BigDecimal,
    val taxAmount: BigDecimal
) {
}
