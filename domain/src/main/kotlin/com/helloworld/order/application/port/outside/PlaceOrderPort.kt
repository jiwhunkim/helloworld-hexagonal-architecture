package com.helloworld.order.application.port.outside

import com.helloworld.order.domain.PlaceOrder

interface PlaceOrderPort {
    fun place(order: PlaceOrder)
}
