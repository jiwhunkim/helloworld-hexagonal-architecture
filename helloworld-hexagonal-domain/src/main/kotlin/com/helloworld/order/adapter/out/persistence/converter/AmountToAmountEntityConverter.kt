package com.helloworld.order.adapter.out.persistence.converter

import com.helloworld.mapper.config.MapperSpringConfig
import com.helloworld.order.domain.Amount
import com.helloworld.order.domain.OrderItem
import com.helloworld.order.domain.entity.AmountEntity
import com.helloworld.order.domain.entity.OrderItemEntity
import org.mapstruct.Mapper

@Mapper(config = MapperSpringConfig::class)
interface AmountToAmountEntityConverter {
    fun convert(source: Amount): AmountEntity
}
