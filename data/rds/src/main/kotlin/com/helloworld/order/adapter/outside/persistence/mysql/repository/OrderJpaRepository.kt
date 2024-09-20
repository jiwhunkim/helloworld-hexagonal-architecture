package com.helloworld.order.adapter.outside.persistence.mysql.repository

import com.helloworld.order.adapter.outside.persistence.mysql.entity.OrderJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface OrderJpaRepository : JpaRepository<OrderJpaEntity, Long>
