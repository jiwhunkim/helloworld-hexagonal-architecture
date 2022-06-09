package com.helloworld.order.domain.entity

import org.springframework.data.repository.CrudRepository

interface CartRepository: CrudRepository<CartEntity, String> {
}
