package ru.elixor.api.utils.jpa

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import java.net.InetAddress


@Converter(autoApply = true)
class InetAddressConverter : AttributeConverter<InetAddress?, String?> {

    override fun convertToDatabaseColumn(attribute: InetAddress?): String? {
        return attribute?.hostAddress
    }

    override fun convertToEntityAttribute(dbData: String?): InetAddress? =
        if (dbData != null) InetAddress.getByName(dbData) else null
}