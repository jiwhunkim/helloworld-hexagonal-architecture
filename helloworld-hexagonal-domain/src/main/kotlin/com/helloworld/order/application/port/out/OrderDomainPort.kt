package com.helloworld.order.application.port.out

import com.helloworld.order.domain.Order
import com.helloworld.order.domain.PlaceOrder

interface OrderDomainPort {
    fun getOrder(id: Long): Order

    fun placeOrder(placeOrder: PlaceOrder): Order
}
