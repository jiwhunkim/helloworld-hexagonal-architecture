package com.helloworld.order.application.port.outside

import com.helloworld.order.domain.Order

interface GetOrderPort {
    fun get(orderId: Long): Order
}
