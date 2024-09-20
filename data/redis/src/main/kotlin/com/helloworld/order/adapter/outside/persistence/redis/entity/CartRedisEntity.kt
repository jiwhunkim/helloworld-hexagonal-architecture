package com.helloworld.order.adapter.outside.persistence.redis.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash
open class CartRedisEntity(id: String, accountId: String) {
    @Id
    var id: String = id
        protected set

    @Indexed
    var accountId: String = accountId
        protected set
}
