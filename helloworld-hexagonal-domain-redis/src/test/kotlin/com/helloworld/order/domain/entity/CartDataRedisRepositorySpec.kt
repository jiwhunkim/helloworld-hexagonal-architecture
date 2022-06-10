package com.helloworld.order.domain.entity

import com.helloworld.redis.config.RedisConfig
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.mockk.every
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@DataRedisTest
@Import(
    RedisConfig::class
)
@ActiveProfiles("test")
class CartDataRedisRepositorySpec : DescribeSpec() {
    @MockkBean
    lateinit var cartDataRedisRepository: CartDataRedisRepository

    init {
        it(".save") {
            every { cartDataRedisRepository.save(any()) }.returns(CartEntity("id", "accountId"))
            var cart = cartDataRedisRepository.save(CartEntity("id", "accountId"))
            cart.id.shouldNotBeNull()
        }
    }
}
