package ru.elixor.api.features.redirect.dto

import ru.elixor.api.entities.link.LinkEntity
import java.util.*

data class LinkRedirectOutputDto(
    val uid: UUID,
    val url: String,
    val password: String?
)

fun LinkEntity.toDto() = LinkRedirectOutputDto(
    uid = uid,
    url = this.url.toString(),
    password = this.password
)
