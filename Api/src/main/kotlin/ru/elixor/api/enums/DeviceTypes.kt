package ru.elixor.api.enums

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

enum class DeviceTypes(val value: String) {
    Mobile("Mobile"),
    Desktop("Desktop");

    @JsonValue
    override fun toString(): String {
        return value
    }

    companion object {
        @JsonCreator
        @JvmStatic
        fun fromValue(value: String): DeviceTypes? {
            return entries.find { it.value.equals(value, ignoreCase = true) }
        }
    }
}