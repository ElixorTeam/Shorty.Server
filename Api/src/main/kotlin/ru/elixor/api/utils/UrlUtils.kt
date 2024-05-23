package ru.elixor.api.utils

import java.net.URL

object UrlUtils {

    fun convert(input: String): URL {
        return URL(input.replace("http://", "https://").let
        { if (!it.startsWith("https://")) "https://$it" else it })
    }

}
