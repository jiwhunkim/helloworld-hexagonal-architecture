package com.helloworld.order.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import java.math.BigDecimal

class PlaceCartSpec: DescribeSpec() {
    init {
        describe(".constructor") {
            it("장바구니 생성시 최소 하나이상의 상품이 있어야 한다.") {
                shouldThrowExactly<IllegalArgumentException> {
                    PlaceCart(memberNo = 1L, items = emptyList())
                }
            }
        }

        describe(".createId") {
            it("현재 시간 nanosecond 와 memberNo 로 cartId 를 생성한다.") {
                val placeCartLineItem = PlaceCartLineItem(
                    productId = 1L,
                    productName = "productName",
                    quantity = 1,
                    price = BigDecimal.TEN
                )
                val id = PlaceCart(memberNo = 1L, items = listOf(placeCartLineItem)).createId()
                id.shouldNotBeNull()
            }
        }
    }
}
