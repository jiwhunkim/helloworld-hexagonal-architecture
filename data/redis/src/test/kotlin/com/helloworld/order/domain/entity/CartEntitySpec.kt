package com.helloworld.order.domain.entity

import com.helloworld.order.adapter.outside.persistence.redis.entity.CartRedisEntity
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
class CartEntitySpec : DescribeSpec() {
    init {
        describe(".constructor") {
            it("construct with id") {
                val cart = CartRedisEntity(id = "id", accountId = "accountId")
                cart.id.shouldNotBeNull()
            }
        }
    }
}
