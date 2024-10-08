package com.helloworld.order.adapter.outside.persistence.mysql.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.ConstraintMode
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ForeignKey
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode

@Entity(name = "orders")
class OrderJpaEntity(
    id: Long = 0,
    orderUser: String,
    orderItems: MutableList<OrderItemJpaEntity>,
) {
    constructor(orderUser: String, orderItems: MutableList<OrderItemJpaEntity>) : this(id = 0, orderUser = orderUser, orderItems = orderItems)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = id
        protected set

    @Column
    var orderUser: String = orderUser
        protected set

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "orderId", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    var orderItems: MutableList<OrderItemJpaEntity> = orderItems
        protected set
}
