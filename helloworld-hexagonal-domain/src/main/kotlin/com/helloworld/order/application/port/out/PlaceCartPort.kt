package com.helloworld.order.application.port.out

import com.helloworld.order.domain.Cart
import com.helloworld.order.domain.PlaceCart

interface PlaceCartPort {
    fun place(cart: PlaceCart): Cart
}
