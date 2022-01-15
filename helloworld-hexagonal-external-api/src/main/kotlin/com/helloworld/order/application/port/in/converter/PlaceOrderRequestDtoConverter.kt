package com.helloworld.order.application.port.`in`.converter

import com.helloworld.mapper.config.MapperSpringConfig
import com.helloworld.order.application.port.`in`.dto.PlaceOrderRequestDto
import com.helloworld.order.domain.PlaceOrder
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.springframework.core.convert.converter.Converter

@Mapper(
    config = MapperSpringConfig::class,
    uses = [PlaceOrderRequestOrderItemDtoConverter::class],
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
interface PlaceOrderRequestDtoConverter : Converter<PlaceOrderRequestDto, PlaceOrder> {
}
