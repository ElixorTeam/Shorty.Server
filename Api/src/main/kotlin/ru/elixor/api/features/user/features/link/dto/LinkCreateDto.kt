package ru.elixor.api.features.user.features.link.dto

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

    val subdomainUid: UUID?,

    @field:NotNull
    val domainUid: UUID,

    @field:NotNull
    @field:ValidURL
    val url: String,

    @field:NotNull
    @field:Size(min = 2, max = 16, message = "Path must be [2, 16] characters")
    val path: String,

    @field:NotNull
    @field:Size(max = 5, min = 0)
    val tags: MutableSet<String>,

    @field:Size(min = 2, max = 16, message = "Password must be [2, 16] characters")
    val password: String?,
)

fun LinkCreateDto.toEntity(): LinkEntity {
    val link = LinkEntity()
    link.title = title
    link.url = UrlUtils.convert(url)
    link.password = password
    link.path = path
    return link;
}
