package com.helloworld.order.domain.entity

import java.math.BigDecimal
import javax.persistence.Embeddable

@Embeddable
class AmountEntity(val amount: BigDecimal) {
}
