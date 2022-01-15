package com.helloworld.order.adapter.out.persistence.converter

import com.helloworld.mapper.config.MapperSpringConfig
import com.helloworld.order.domain.Order
import com.helloworld.order.domain.entity.OrderEntity
import org.mapstruct.DecoratedWith
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.springframework.core.convert.converter.Converter

@Mapper(
    config = MapperSpringConfig::class,
    uses = [OrderItemToOrderItemEntityConverter::class],
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
@DecoratedWith(OrderMapperDecorator::class)
interface OrderToOrderEntityConverter : Converter<OrderEntity, Order> {
    override fun convert(source: OrderEntity): Order
}
