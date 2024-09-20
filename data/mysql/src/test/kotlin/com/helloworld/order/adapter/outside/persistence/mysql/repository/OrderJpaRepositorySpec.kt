package com.helloworld.order.adapter.outside.persistence.mysql.repository

import com.helloworld.MysqlApplication
import com.helloworld.config.mysql.QueryDslConfig
import com.helloworld.config.mysql.audit.AuditorAwareImpl
import com.helloworld.order.adapter.outside.persistence.mysql.entity.OrderJpaEntity
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration

@ActiveProfiles("test")
@DataJpaTest
@Import(AuditorAwareImpl::class, QueryDslConfig::class, JacksonAutoConfiguration::class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = [MysqlApplication::class])
class OrderJpaRepositorySpec: DescribeSpec() {
    @Autowired
    lateinit var orderJpaRepository: OrderJpaRepository
    init {
        it("findById") {
            val order = OrderJpaEntity("orderUser", mutableListOf())
            val saved = orderJpaRepository.save(order)

            val result = orderJpaRepository.findById(saved.id)
            result.get().shouldNotBeNull()
        }
    }
}
