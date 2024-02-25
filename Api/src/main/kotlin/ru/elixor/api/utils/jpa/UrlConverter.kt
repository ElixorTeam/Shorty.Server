package ru.elixor.api.utils.jpa

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import java.net.URL

@Converter
class UrlConverter : AttributeConverter<URL, String> {
    override fun convertToDatabaseColumn(attribute: URL?): String? {
        return attribute?.toString()
    }

    override fun convertToEntityAttribute(dbData: String?): URL? {
        return dbData?.let { URL(it) }
    }
}