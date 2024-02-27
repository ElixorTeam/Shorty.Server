package ru.elixor.api.features.link.dto

import java.util.*

class LinkCreateDto(
    val title: String,
    val subdomain: String,
    val domainUid: UUID,
    val url: String,
    val password: String,
)