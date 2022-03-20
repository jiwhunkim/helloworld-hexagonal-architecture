package com.helloworld.order.domain

class PlacePayment(
    val orderId: Long,
    val placePaymentMethods: MutableList<PlacePaymentMethod>
) {
}
