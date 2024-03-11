package ru.elixor.api.features.link.dto

import ru.elixor.api.entities.link.LinkEntity
import java.util.*

data class LinkOutputDto(
    val uid: UUID,
    val title: String,
    val url: String,
    var subdomain: String,
    var domainUid: UUID,
    var password: String?,
    var updateDt: Date,
    var createDt: Date
)


fun LinkEntity.toDto() = LinkOutputDto(
    uid = uid!!,
    title = title,
    url = url.toString(),
    subdomain = subdomain,
    domainUid = domain!!.uid!!,
    createDt = createDt!!,
    updateDt = updateDt!!,
    password = password,
)