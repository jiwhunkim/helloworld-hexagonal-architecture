package com.helloworld.order.adapter.outside.persistence.mysql.entity

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class ItemAttributeJpaConverter(private val objectMapper: ObjectMapper) : AttributeConverter<List<ItemAttributeJpaEntity>, String> {
    override fun convertToDatabaseColumn(attribute: List<ItemAttributeJpaEntity>): String {
        return objectMapper.writeValueAsString(attribute)
    }

    override fun convertToEntityAttribute(dbData: String?): List<ItemAttributeJpaEntity> {
        return objectMapper.readValue(dbData, object : TypeReference<List<ItemAttributeJpaEntity>>() {})
    }
}
