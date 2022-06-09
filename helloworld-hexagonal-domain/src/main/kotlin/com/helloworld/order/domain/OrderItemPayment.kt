package com.helloworld.order.domain

import java.math.BigDecimal

class OrderItemPayment(
    val paymentMethod: PaymentMethod,
    val orderItem: OrderItem,
    val taxBaseAmount: BigDecimal,
    val taxFreeAmount: BigDecimal,
    val taxAmount: BigDecimal
) {
    companion object {
        fun calculate(list: List<PaymentLineItem>, list2: List<OrderItem>): List<OrderItemPayment> {
            val orderItemTotal2 = list2.sumOf { it.totalTaxBaseAmount }

            // 카드 8000원 포인트 3000원 쿠폰 2000원
            // 물건 A = 7000원
            // 카드 4900 포인트 2100
            // 물건 B = 3000원
            // 카드 2100 포인트 900
            // 물건 C = 3000원
            // 카드 1000 쿠폰 2000
            // 쿠폰 대상 선별 쿠폰 금액 채움


            val result = mutableListOf<OrderItemPayment>()

            list.forEach { paymentLineItem ->
                if(paymentLineItem.paymentMethod == PaymentMethod.COUPON) {
                    // 쿠폰 타겟 선별
                    // 쿠폰 대상 sum
                    // 쿠폰 대상 퍼센트
                    // 쿠폰 대상 가격 책정
                } else {
                    // 쿠폰 대상 확인 쿠폰 대상이면
                    // 쿠폰금액 제하고 오브젝트 만듬
                    // 물건합 - 쿠폰금액 
                }
            }

            list2.forEach { orderItem ->
                val percentTotal2 = orderItem.totalTaxBaseAmount.divide(orderItemTotal2)
                list.forEach { paymentLineItem ->
                    val item = OrderItemPayment(
                        paymentMethod = paymentLineItem.paymentMethod,
                        orderItem = orderItem,
                        taxBaseAmount = paymentLineItem.taxBaseAmount.multiply(percentTotal2),
                        taxFreeAmount = BigDecimal.ZERO,
                        taxAmount = BigDecimal.ZERO
                    )
                    result.add(item)
                }
            }
            return result
        }
    }
}
