package com.helloworld.order.adapter.out.persistence.converter

import com.helloworld.order.domain.Order
import com.helloworld.order.domain.entity.OrderEntity
import org.springframework.beans.factory.annotation.Autowired

abstract class OrderMapperDecorator : OrderToOrderEntityConverter {
    @Autowired
    lateinit var delegate: OrderToOrderEntityConverter

    override fun convert(source: OrderEntity): Order {
        return delegate.convert(source)
    }
}
