package com.helloworld.order.domain

class PlaceCart(
    val memberNo: Long,
    val items: List<CartLineItem>
) {
    init {
        require(items.isNotEmpty()) { "require items" }
    }
}
