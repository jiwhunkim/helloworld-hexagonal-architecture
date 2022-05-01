package com.helloworld.order.domain

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class PlaceCart(
    val memberNo: Long,
    val items: List<PlaceCartLineItem>
) {
    init {
        require(items.isNotEmpty()) { "require items" }
    }

    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSSSS")

    fun createId(): String {
        return "${ZonedDateTime.now().format(formatter)}|${memberNo}"
    }
}
