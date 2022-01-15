package com.helloworld.order.application.service.converter

import com.helloworld.mapper.config.MapperSpringConfig
import com.helloworld.order.application.service.dto.OrderItemDto
import com.helloworld.order.domain.OrderItem
import org.mapstruct.Mapper
import org.springframework.core.convert.converter.Converter

@Mapper(config = MapperSpringConfig::class)
interface OrderItemToOrderItemDtoConverter : Converter<OrderItem, OrderItemDto> {
}
