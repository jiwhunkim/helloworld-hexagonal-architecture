package com.helloworld.order.adapter.out.persistence

import com.helloworld.order.adapter.out.persistence.converter.OrderToOrderEntityConverter
import com.helloworld.order.application.port.out.OrderDomainPort
import com.helloworld.order.domain.Order
import com.helloworld.order.domain.PlaceOrder
import com.helloworld.order.domain.entity.OrderEntity
import com.helloworld.order.domain.entity.OrderItemEntity
import com.helloworld.order.domain.entity.OrderJpaRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Component
class OrderPersistenceAdapter(
    private val orderJpaRepository: OrderJpaRepository,
    private val orderConverter: OrderToOrderEntityConverter
) : OrderDomainPort {

    @Transactional(readOnly = true)
    override fun getOrder(id: Long): Order {
        val order = orderJpaRepository.findById(id).orElseThrow { NoSuchElementException("not exist") }
        return orderConverter.convert(order)
    }

    override fun placeOrder(placeOrder: PlaceOrder): Order {
        val orderItems = placeOrder.orderItems.map {
            OrderItemEntity(
                productId = it.productId,
                productName = it.productName,
                quantity = it.quantity,
                salePrice = it.salePrice,
                taxBaseAmount = it.taxBaseAmount,
                taxFreeAmount = it.taxFreeAmount,
                taxAmount = it.taxAmount,
                sellerId = it.sellerId
            )
        }.toMutableList()

        val createOrder = OrderEntity(orderUser = placeOrder.orderUser, orderItems = orderItems)
        val saved = orderJpaRepository.save(createOrder)
        return orderConverter.convert(saved)

    }
}
