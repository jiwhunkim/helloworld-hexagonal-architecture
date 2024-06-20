package com.helloworld.order.domain

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class PlaceCart(
    val memberNo: String,
    val items: List<PlaceCartLineItem>,
) {
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSSSS")

    fun createId(): String {
        return "${ZonedDateTime.now().format(formatter)}|$memberNo"
    }
}
