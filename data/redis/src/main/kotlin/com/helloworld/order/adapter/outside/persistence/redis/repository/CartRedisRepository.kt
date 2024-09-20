package com.helloworld.order.adapter.outside.persistence.redis.repository

import com.helloworld.order.adapter.outside.persistence.redis.entity.CartRedisEntity
import org.springframework.data.repository.CrudRepository

interface CartRedisRepository : CrudRepository<CartRedisEntity, String>
