package com.helloworld.order.application.port.out

import com.helloworld.order.domain.PlaceOrder

interface PlaceOrderPort {
    fun place(order: PlaceOrder)
}
