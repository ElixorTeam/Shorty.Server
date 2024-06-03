package ru.elixor.api.features.user.features.link.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import ru.elixor.api.entities.link.LinkEntity
import ru.elixor.api.utils.validators.ValidURL
import java.util.*

data class LinkCreateDto(

    @field:NotNull
    @field:Size(min = 2, max = 64, message = "Title must be [2, 64] characters")
    val title: String,

    val subdomainUid: UUID?,

    @field:NotNull
    val domainUid: UUID,

    @field:Size(max = 5, min = 1)
    val urls: MutableSet<@ValidURL String>,

    @field:NotNull
    @field:Size(min = 2, max = 16, message = "Path must be [2, 16] characters")
    val path: String,

    @field:NotNull
    @field:Size(max = 5, min = 0)
    val tags: MutableSet<@Size(max=16, min=1) String>,

    @field:Size(min = 2, max = 16, message = "Password must be [2, 16] characters")
    val password: String?,
)

fun LinkCreateDto.toEntity(): LinkEntity {
    val link = LinkEntity()
    link.title = title
    link.password = password
    link.path = path
    return link;
}
