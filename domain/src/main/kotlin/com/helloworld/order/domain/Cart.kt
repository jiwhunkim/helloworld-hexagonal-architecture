package com.helloworld.order.domain

class Cart(
    val id: String,
    val memberNo: String,
    val items: List<CartLineItem>,
)
