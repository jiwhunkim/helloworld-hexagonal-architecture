package com.helloworld.order.adapter.outside.persistence

import com.helloworld.DomainApplication
import com.helloworld.order.adapter.out.persistence.OrderPersistenceAdapter
import com.helloworld.order.domain.PlaceOrder
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles(value = ["redis", "rds", "test"])
@SpringBootTest(classes = [DomainApplication::class])
class OrderPersistenceAdapterIntSpec : DescribeSpec() {
    @Autowired
    private lateinit var orderPersistenceAdapter: OrderPersistenceAdapter

    init {
        describe(".placeOrder2") {
            it("repository save method call") {
                val order = orderPersistenceAdapter.placeOrder(PlaceOrder("orderUser", mutableListOf()))
                order.shouldNotBeNull()
            }
        }
    }


}
