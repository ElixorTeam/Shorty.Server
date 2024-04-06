package ru.elixor.api.utils

import java.net.URL
import java.util.*

class DefaultTypesUtil {
    companion object {
        val date: Date = Date(Long.MIN_VALUE)
        val guid: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000")
        val url: URL = URL("https://example.com")
    }
}