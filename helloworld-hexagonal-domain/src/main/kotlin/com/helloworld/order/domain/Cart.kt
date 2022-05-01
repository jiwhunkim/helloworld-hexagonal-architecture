package com.helloworld.order.domain

class Cart(
    val id: String,
    val memberNo: Long,
    val items: List<CartLineItem>
) {
}
