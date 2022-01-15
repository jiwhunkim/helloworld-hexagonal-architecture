package com.helloworld.order.domain.entity

import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import javax.persistence.*

@Entity(name = "orders")
class OrderEntity(
    id: Long = 0,
    orderUser: String,
    orderItems: MutableList<OrderItemEntity>
) {
    constructor(orderUser: String, orderItems: MutableList<OrderItemEntity>): this(id = 0, orderUser = orderUser, orderItems = orderItems)

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
    var orderItems: MutableList<OrderItemEntity> = orderItems
        protected set
}
