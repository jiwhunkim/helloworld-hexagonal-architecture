package com.helloworld.order.application.port.out

import com.helloworld.order.adapter.out.persistence.CartPersistenceAdapter
import com.helloworld.order.domain.PlaceCart
import com.helloworld.order.domain.entity.CartDataRedisRepository
import com.helloworld.order.domain.entity.CartEntity
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@Import(CartPersistenceAdapter::class)
class PlaceCartPortSpec : DescribeSpec() {
    @MockkBean
    lateinit var cartDataRedisRepository: CartDataRedisRepository

    @Autowired
    lateinit var placeCartPort: PlaceCartPort

    init {
        it(".place") {
            val placeCart = PlaceCart("memberNo", emptyList())
            val id = placeCart.createId()
            every { cartDataRedisRepository.save(any()) } returns CartEntity(id = id, accountId = placeCart.memberNo)
            val saved = placeCartPort.place(placeCart)
            saved.id.shouldBe(id)
            saved.memberNo.shouldBe(placeCart.memberNo)
        }
    }

}
