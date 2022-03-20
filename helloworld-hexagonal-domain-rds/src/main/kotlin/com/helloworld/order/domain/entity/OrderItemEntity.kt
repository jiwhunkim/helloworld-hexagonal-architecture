package com.helloworld.order.domain.entity

import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "order_items")
class OrderItemEntity(
    productId: Long,
    productName: String,
    quantity: Int,
    price: BigDecimal,
    amount: AmountEntity,
    id: Long = 0,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = id
        protected set

    @Column
    var productId: Long = productId
        protected set

    @Column
    var productName: String = productName
        protected set

    @Column
    var quantity: Int = quantity
        protected set

    @Column
    var price: BigDecimal = price
        protected set

    @Column
    var amount: AmountEntity = amount
        protected set
}
