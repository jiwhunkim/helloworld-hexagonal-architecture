package com.helloworld.config.mysql

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EntityScan(basePackages = ["com.helloworld.*.adapter.outside.persistence.mysql"])
@EnableJpaRepositories(basePackages = ["com.helloworld.*.adapter.outside.persistence.mysql"])
@EnableTransactionManagement
class JpaConfig
