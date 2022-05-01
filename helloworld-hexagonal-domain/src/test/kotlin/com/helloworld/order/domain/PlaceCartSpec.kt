package com.helloworld.order.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec

class PlaceCartSpec: DescribeSpec() {
    init {
        describe("constructor") {
            it("장바구니 생성시 최소 하나이상의 상품이 있어야 한다.") {
                shouldThrowExactly<IllegalArgumentException> {
                    PlaceCart(memberNo = 1L, items = emptyList())
                }
            }
        }
    }
}
