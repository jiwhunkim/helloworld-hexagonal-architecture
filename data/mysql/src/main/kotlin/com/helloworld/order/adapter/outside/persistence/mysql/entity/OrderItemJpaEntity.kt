package com.helloworld.order.adapter.outside.persistence.mysql.entity

import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal

@Entity(name = "order_items")
class OrderItemJpaEntity(
    productId: Long,
    productName: String,
    quantity: Int,
    salePrice: BigDecimal,
    taxBaseAmount: BigDecimal,
    taxFreeAmount: BigDecimal,
    taxAmount: BigDecimal,
    sellerId: Long,
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
    var salePrice: BigDecimal = salePrice
        protected set

    @Column
    var taxBaseAmount: BigDecimal = taxBaseAmount
        protected set

    @Column
    var taxFreeAmount: BigDecimal = taxFreeAmount
        protected set

    @Column
    var taxAmount: BigDecimal = taxAmount
        protected set

    @Column
    var sellerId: Long = sellerId
        protected set

    @Column
    @Convert(converter = ItemAttributeJpaConverter::class)
    var attribute: List<ItemAttributeJpaEntity> = mutableListOf()
        protected set
}
