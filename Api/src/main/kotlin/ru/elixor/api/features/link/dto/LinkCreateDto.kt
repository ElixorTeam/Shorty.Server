package ru.elixor.api.features.link.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import ru.elixor.api.entities.link.LinkEntity
import ru.elixor.api.utils.UrlUtils
import ru.elixor.api.utils.validators.ValidURL
import java.util.*

data class LinkCreateDto(

    @field:NotNull
    @field:Size(min = 2, max = 64, message = "Title must be [2, 64] characters")
    val title: String,

    @field:NotNull
    @field:Size(min = 4, max = 12)
    val subdomain: String,

    @field:NotNull
    val domainUid: UUID,

    @field:NotNull
    @field:ValidURL
    val url: String,

    @field:Size(min = 2, max = 16, message = "Password must be [2, 16] characters")
    val password: String?,
)

fun LinkCreateDto.toEntity(): LinkEntity {
    val link = LinkEntity()
    link.title = title
    link.subdomain = subdomain
    link.url = UrlUtils.convert(url)
    link.password = password
    return link;
}