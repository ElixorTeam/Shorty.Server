package ru.elixor.api.features.anonymus.features.redirect.dto

import ru.elixor.api.entities.link.LinkEntity
import java.util.*

data class LinkRedirectOutputDto(
    val uid: UUID,
    val urls: MutableSet<String>,
    val password: String?
)

fun LinkEntity.toDto() = LinkRedirectOutputDto(
    uid = uid,
    urls = urls.map { it.url.toString() }.toHashSet(),
    password = this.password
)
