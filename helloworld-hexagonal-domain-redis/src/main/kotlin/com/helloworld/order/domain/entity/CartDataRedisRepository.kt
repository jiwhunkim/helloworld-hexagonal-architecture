package com.helloworld.order.domain.entity

import org.springframework.data.repository.CrudRepository

interface CartDataRedisRepository : CrudRepository<CartEntity, String>
