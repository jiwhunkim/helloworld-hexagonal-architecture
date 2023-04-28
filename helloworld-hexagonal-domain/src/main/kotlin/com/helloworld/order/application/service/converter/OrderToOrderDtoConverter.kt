package com.helloworld.order.application.service.converter

import com.helloworld.mapper.config.MapperSpringConfig
import com.helloworld.order.application.service.dto.OrderDto
import com.helloworld.order.domain.Order
import org.mapstruct.Mapper
import org.springframework.core.convert.converter.Converter

@Mapper(config = MapperSpringConfig::class)
interface OrderToOrderDtoConverter : Converter<Order, OrderDto>
