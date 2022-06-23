package com.helloworld.order.adapter.out.persistence

import com.helloworld.order.application.port.outside.PlaceCartPort
import com.helloworld.order.domain.Cart
import com.helloworld.order.domain.PlaceCart
import com.helloworld.order.domain.entity.CartDataRedisRepository
import com.helloworld.order.domain.entity.CartEntity
import org.springframework.stereotype.Service

@Service
class CartPersistenceAdapter(
    private val cartDataRedisRepository: CartDataRedisRepository
) : PlaceCartPort {
    override fun place(cart: PlaceCart): Cart {
        val saved = cartDataRedisRepository.save(CartEntity(id = cart.createId(), accountId = cart.memberNo))
        return Cart(id = saved.id, memberNo = saved.accountId, items = emptyList())
    }
}
