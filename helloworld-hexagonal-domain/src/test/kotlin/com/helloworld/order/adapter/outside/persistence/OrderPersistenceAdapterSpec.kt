package com.helloworld.order.adapter.outside.persistence

import com.helloworld.order.adapter.outside.persistence.converter.OrderItemToOrderItemEntityConverterImpl
import com.helloworld.order.adapter.outside.persistence.converter.OrderToOrderEntityConverterImpl
import com.helloworld.order.adapter.outside.persistence.converter.OrderToOrderEntityConverterImpl_
import com.helloworld.order.domain.entity.OrderEntity
import com.helloworld.order.domain.entity.OrderJpaRepository
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.every
import io.mockk.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import java.util.*

@ActiveProfiles("test")
@Import(
    OrderPersistenceAdapter::class,
    OrderJpaRepository::class,
    OrderItemToOrderItemEntityConverterImpl::class,
    OrderToOrderEntityConverterImpl::class,
    OrderToOrderEntityConverterImpl_::class
)
class OrderPersistenceAdapterSpec : DescribeSpec() {
    @MockkBean
    private lateinit var orderJpaRepository: OrderJpaRepository

    @Autowired
    private lateinit var orderPersistenceAdapter: OrderPersistenceAdapter

    init {
        describe(".getOrder") {
            it("repository find method call") {
                every { orderJpaRepository.findById(any()) }.returns(
                    Optional.of(
                        OrderEntity(
                            id = 1,
                            orderUser = "orderUser",
                            orderItems = mutableListOf()
                        )
                    )
                )

                orderPersistenceAdapter.getOrder(1)
                verify { orderJpaRepository.findById(any()) }
            }
        }
    }
}
