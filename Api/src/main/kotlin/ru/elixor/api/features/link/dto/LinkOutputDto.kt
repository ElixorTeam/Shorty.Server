package ru.elixor.api.features.link.dto

import java.time.LocalDateTime
import java.util.UUID

class LinkOutputDto (
    val id: UUID,
    val title: String,
    var shortSubDomain: String,
    var prefix: String,
    var createDt: LocalDateTime,
    var changeDt: LocalDateTime
)
