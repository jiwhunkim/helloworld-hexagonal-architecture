package com.helloworld.order.adapter.out.persistence

import com.helloworld.DomainApplication
import com.helloworld.order.adapter.out.persistence.converter.OrderItemToOrderItemEntityConverterImpl
import com.helloworld.order.adapter.out.persistence.converter.OrderToOrderEntityConverterImpl
import com.helloworld.order.adapter.out.persistence.converter.OrderToOrderEntityConverterImpl_
import com.helloworld.order.application.port.out.OrderDomainPort
import com.helloworld.order.domain.PlaceOrder
import com.helloworld.order.domain.entity.OrderJpaRepository
import com.helloworld.rds.config.RdsConfig
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional

@ActiveProfiles(value = ["test"])
@Import(
    OrderPersistenceAdapter::class,
    RdsConfig::class,
    OrderItemToOrderItemEntityConverterImpl::class,
    OrderToOrderEntityConverterImpl::class,
    OrderToOrderEntityConverterImpl_::class
)
@DataJpaTest
@ContextConfiguration(classes = [DomainApplication::class])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderPersistenceAdapterPlaceSpec() : DescribeSpec() {
    @Autowired
    lateinit var orderPersistenceAdapter: OrderDomainPort

    init {
        describe(".placeOrder") {
            it("repository find method call") {
                val order = orderPersistenceAdapter.placeOrder(PlaceOrder("orderUser", mutableListOf()))
                order.shouldNotBeNull()
                orderPersistenceAdapter.placeOrder(PlaceOrder("orderUser", mutableListOf()))
                orderPersistenceAdapter.placeOrder(PlaceOrder("orderUser", mutableListOf()))
                orderPersistenceAdapter.placeOrder(PlaceOrder("orderUser", mutableListOf()))
                orderPersistenceAdapter.placeOrder(PlaceOrder("orderUser", mutableListOf()))
                val order2 = orderPersistenceAdapter.placeOrder(PlaceOrder("orderUser", mutableListOf()))
                order2.shouldNotBeNull()
            }
        }
    }
}
