package com.helloworld.order.application.port.inside.converter

import com.helloworld.mapper.config.MapperSpringConfig
import com.helloworld.order.application.port.inside.dto.PlaceOrderRequestOrderItemDto
import com.helloworld.order.domain.PlaceOrderItem
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.springframework.core.convert.converter.Converter

@Mapper(
    config = MapperSpringConfig::class,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
interface PlaceOrderRequestOrderItemDtoConverter : Converter<PlaceOrderRequestOrderItemDto, PlaceOrderItem> {
    override fun convert(source: PlaceOrderRequestOrderItemDto): PlaceOrderItem
}
