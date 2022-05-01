package com.helloworld.order.domain

import java.math.BigDecimal

class PlaceCartLineItem(
    val productId: Long,
    val productName: String,
    val quantity: Int,
    val price: BigDecimal
) {
}
