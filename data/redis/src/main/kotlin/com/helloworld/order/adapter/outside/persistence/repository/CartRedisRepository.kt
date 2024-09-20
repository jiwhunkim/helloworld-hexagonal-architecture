package com.helloworld.order.adapter.outside.persistence.repository

import com.helloworld.order.adapter.outside.persistence.entity.CartRedisEntity
import org.springframework.data.repository.CrudRepository

interface CartRedisRepository : CrudRepository<CartRedisEntity, String>
