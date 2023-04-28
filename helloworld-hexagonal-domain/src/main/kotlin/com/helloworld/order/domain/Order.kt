package com.helloworld.order.domain

class Order(
    val id: Long,
    val orderUser: String,
    val orderItems: MutableList<OrderItem>
)
