package com.helloworld.order.application.port.out

import com.helloworld.order.domain.Order

interface GetOrderPort {
    fun get(orderId: Long): Order
}
