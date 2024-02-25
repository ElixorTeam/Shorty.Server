package ru.elixor.api.features.link.dto

import ru.elixor.api.entities.link.LinkEntity
import java.time.LocalDateTime
import java.util.*

//class LinkOutputDto (
//    val uid: UUID,
//    val title: String,
//    var subdomain: String,
//    var domain: String,
//    var url: String,
//    var createDt: Date,
//    var updateDt: Date
//


class LinkOutputDto (
    val id: UUID,
    val title: String,
    var shortSubDomain: String,
    var prefix: String,
    var createDt: LocalDateTime,
    var changeDt: LocalDateTime
)


fun LinkEntity.toDto() = LinkOutputDto(
    id = uid!!,
    title = title,
    shortSubDomain = "",
    prefix = "",
    createDt = LocalDateTime.now(),
    changeDt = LocalDateTime.now()
)