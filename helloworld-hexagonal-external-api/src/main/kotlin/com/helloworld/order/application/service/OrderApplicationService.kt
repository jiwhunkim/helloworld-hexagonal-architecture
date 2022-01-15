package com.helloworld.order.application.service

import com.helloworld.order.application.port.`in`.OrderApplicationPort
import com.helloworld.order.application.port.`in`.OrderApplicationServicePort
import com.helloworld.order.application.port.`in`.converter.PlaceOrderRequestDtoConverter
import com.helloworld.order.application.port.`in`.dto.GetOrderResponseDto
import com.helloworld.order.application.port.`in`.dto.PlaceOrderRequestDto
import org.springframework.stereotype.Service

@Service
class OrderApplicationService(
    val orderApplicationPort: OrderApplicationPort,
    val placeOrderRequestDtoConverter: PlaceOrderRequestDtoConverter
) : OrderApplicationServicePort {
    override fun getOrder(id: Long): GetOrderResponseDto {
        val orderDto = orderApplicationPort.getOrder(id)
        return GetOrderResponseDto(orderDto)
    }

    override fun placeOrder(placeOrderRequestDto: PlaceOrderRequestDto): GetOrderResponseDto {
        val placeOrder = placeOrderRequestDtoConverter.convert(placeOrderRequestDto) ?: throw IllegalArgumentException("wrong orderDto")
        val result = orderApplicationPort.placeOrder(placeOrder)
        return GetOrderResponseDto(result)
    }
}
