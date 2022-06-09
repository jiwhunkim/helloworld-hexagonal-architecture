package com.helloworld.order.adapter.out.persistence

import com.helloworld.order.application.port.out.PlaceCartPort
import com.helloworld.order.domain.Cart
import com.helloworld.order.domain.PlaceCart

class CartPersistenceAdapter: PlaceCartPort {
    override fun place(cart: PlaceCart): Cart {
        return Cart(id = "id", memberNo = "memberNo", items = emptyList())
    }
}
