package com.helloworld.order.domain.entity

import com.helloworld.redis.config.RedisConfig
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.mockk.every
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@DataRedisTest
@Import(
    RedisConfig::class
)
@ActiveProfiles("test")
class CartRepositorySpec : DescribeSpec() {
    @MockkBean
    lateinit var cartRepository: CartRepository

    init {
        it(".save") {
            every { cartRepository.save(any()) }.returns(CartEntity("id", "accountId"))
            var cart = cartRepository.save(CartEntity("id", "accountId"))
            cart.id.shouldNotBeNull()
        }
    }
}
