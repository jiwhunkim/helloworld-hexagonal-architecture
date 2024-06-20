package com.helloworld.order.domain

import java.math.BigDecimal

class PaymentLineItem(
    val paymentMethod: PaymentMethod,
    val taxBaseAmount: BigDecimal,
    val taxFreeAmount: BigDecimal,
    val taxAmount: BigDecimal,
)
