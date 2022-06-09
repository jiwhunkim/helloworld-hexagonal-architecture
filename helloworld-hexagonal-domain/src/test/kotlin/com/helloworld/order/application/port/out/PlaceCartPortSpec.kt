package com.helloworld.order.application.port.out

import com.helloworld.order.adapter.out.persistence.CartPersistenceAdapter
import com.helloworld.order.domain.PlaceCart
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@Import(CartPersistenceAdapter::class)
class PlaceCartPortSpec: DescribeSpec() {
    @Autowired
    lateinit var placeCartPort: PlaceCartPort

    init {
        it(".place") {
            val saved = placeCartPort.place(PlaceCart("memberNo", emptyList()))
            saved.id.shouldNotBeNull()
        }
    }

}
