package com.helloworld.order.application.service


import com.helloworld.order.application.port.`in`.OrderApplicationPort
import com.helloworld.order.application.port.out.OrderDomainPort
import com.helloworld.order.application.service.converter.OrderToOrderDtoConverter
import com.helloworld.order.application.service.dto.OrderDto
import com.helloworld.order.domain.PlaceOrder
import org.springframework.stereotype.Service

@Service
class OrderDomainApplicationService(
    private val orderDomainPort: OrderDomainPort,
    private val orderToOrderDtoConverter: OrderToOrderDtoConverter
) : OrderApplicationPort {
    override fun getOrder(id: Long): OrderDto {
        val order = orderDomainPort.getOrder(id)
        return orderToOrderDtoConverter.convert(order) ?: throw IllegalArgumentException("wrong order")
    }

    override fun placeOrder(placeOrder: PlaceOrder): OrderDto {
        val order = orderDomainPort.placeOrder(placeOrder)
        return orderToOrderDtoConverter.convert(order) ?: throw IllegalArgumentException("wrong order")
    }
}
