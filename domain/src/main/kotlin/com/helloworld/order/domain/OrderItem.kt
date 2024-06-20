package com.helloworld.order.domain

import java.math.BigDecimal

class OrderItem(
    val id: Long,
    val productId: Long,
    val productName: String,
    val quantity: Int,
    val salePrice: BigDecimal,
    val taxBaseAmount: BigDecimal,
    val taxFreeAmount: BigDecimal,
    val taxAmount: BigDecimal,
    val sellerId: Long,
) {
    val totalAmount: BigDecimal
        get() = (taxBaseAmount + taxAmount + taxFreeAmount) * BigDecimal.valueOf(quantity.toDouble())

    val totalTaxBaseAmount: BigDecimal
        get() = taxBaseAmount * BigDecimal.valueOf(quantity.toLong())

    val totalTaxFreeAmount: BigDecimal
        get() = taxFreeAmount * BigDecimal.valueOf(quantity.toLong())

    val totalTaxAmount: BigDecimal
        get() = taxAmount * BigDecimal.valueOf(quantity.toLong())
}
