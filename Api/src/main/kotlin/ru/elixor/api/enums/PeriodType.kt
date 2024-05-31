package ru.elixor.api.enums

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

enum class PeriodType(val value: String) {
    Day("Day"),
    Week("Week"),
    Month("Month"),
    Year("Year");

    @JsonValue
    override fun toString(): String {
        return value
    }

    companion object {
        @JsonCreator
        @JvmStatic
        fun fromValue(value: String): PeriodType? {
            return entries.find { it.value.equals(value, ignoreCase = true) }
        }
    }
}