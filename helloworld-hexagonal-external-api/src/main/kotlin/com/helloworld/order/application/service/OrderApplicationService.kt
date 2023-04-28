package com.helloworld.order.application.service

import com.helloworld.order.application.port.inside.OrderApplicationPort
import com.helloworld.order.application.port.inside.OrderApplicationServicePort
import com.helloworld.order.application.port.inside.converter.PlaceOrderRequestDtoConverter
import com.helloworld.order.application.port.inside.dto.GetOrderResponseDto
import com.helloworld.order.application.port.inside.dto.PlaceOrderRequestDto
import org.springframework.stereotype.Service

@Service
class OrderApplicationService(
    val orderApplicationPort: OrderApplicationPort,
    val placeOrderRequestDtoConverter: PlaceOrderRequestDtoConverter
) : OrderApplicationServicePort {
    override fun getOrder(id: Long): GetOrderResponseDto {
        val orderDto = orderApplicationPort.getOrderDto(id)
        return GetOrderResponseDto(orderDto)
    }

    override fun placeOrder(placeOrderRequestDto: PlaceOrderRequestDto): GetOrderResponseDto {
        val placeOrder = placeOrderRequestDtoConverter.convert(placeOrderRequestDto) ?: throw IllegalArgumentException("wrong orderDto")
        val result = orderApplicationPort.placeOrderDto(placeOrder)
        return GetOrderResponseDto(result)
    }
}
