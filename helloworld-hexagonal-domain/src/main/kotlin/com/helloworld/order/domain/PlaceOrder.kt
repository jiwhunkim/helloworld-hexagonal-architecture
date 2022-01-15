package com.helloworld.order.domain

class PlaceOrder(
    val orderUser: String,
    val orderItems: MutableList<PlaceOrderItem>
)
