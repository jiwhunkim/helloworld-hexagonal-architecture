package com.helloworld.order.domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
class OrderSpec : DescribeSpec() {
    init {
        describe("constructor") {
            it("create order with id") {
                val order = Order(
                    id = 1,
                    orderUser = "orderUser",
                    orderItems = mutableListOf()
                )
                order.id.shouldNotBeNull()
            }
        }
    }
}
