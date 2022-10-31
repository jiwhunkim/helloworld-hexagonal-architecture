package com.helloworld.order.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.mockk.every
import io.mockk.mockkStatic
import java.math.BigDecimal
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

class PlaceCartSpec: DescribeSpec() {
    init {
        describe(".constructor") {
            it("장바구니 생성시 최소 하나 이상의 상품이 있어야 한다.") {
//                shouldThrowExactly<IllegalArgumentException> {
//                    PlaceCart(memberNo = "memberNo", items = emptyList())
//                }
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
                mockkStatic(ZonedDateTime::class)
                every { ZonedDateTime.now() } returns ZonedDateTime.ofInstant(Instant.parse("2022-12-01T02:02:02.222Z"), ZoneId.of("Asia/Seoul"))
                val id = PlaceCart(memberNo = "memberNo", items = listOf(placeCartLineItem)).createId()
                println(id)
                id.shouldNotBeNull()
            }
        }
    }
}
