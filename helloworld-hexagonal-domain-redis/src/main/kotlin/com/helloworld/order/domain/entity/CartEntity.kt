package com.helloworld.order.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash
open class CartEntity(id: String, accountId: String) {
    @Id
    var id: String = id
        protected set

    @Indexed
    var accountId: String = accountId
        protected set
}
