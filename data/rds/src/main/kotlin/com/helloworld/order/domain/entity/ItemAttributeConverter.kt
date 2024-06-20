package com.helloworld.order.domain.entity

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class ItemAttributeConverter(private val objectMapper: ObjectMapper) : AttributeConverter<List<ItemAttribute>, String> {
    override fun convertToDatabaseColumn(attribute: List<ItemAttribute>): String {
        return objectMapper.writeValueAsString(attribute)
    }

    override fun convertToEntityAttribute(dbData: String?): List<ItemAttribute> {
        return objectMapper.readValue(dbData, object : TypeReference<List<ItemAttribute>>() {})
    }
}
