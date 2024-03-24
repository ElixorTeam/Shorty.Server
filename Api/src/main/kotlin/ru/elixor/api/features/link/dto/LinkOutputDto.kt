package ru.elixor.api.features.link.dto

import ru.elixor.api.entities.link.LinkEntity
import java.util.*


data class LinkOutputDto(
    val uid: UUID,
    val title: String,
    val url: String,
    val tags: MutableSet<String>,
    val subdomain: String,
    val domainUid: UUID,
    val password: String?,
    val updateDt: Date,
    val createDt: Date
)

internal fun LinkEntity.toLinkDto() = LinkOutputDto(
    uid = uid,
    title = title,
    url = url.toString(),
    tags = tags.map { it.title }.toHashSet(),
    subdomain = subdomain,
    domainUid = domain.uid,
    createDt = createDt,
    updateDt = updateDt,
    password = password,
)

// region Wrappers

data class LinkListOutputDtoWrapper(
    val data: List<LinkOutputDto>,
)

data class SingleLinkOutputDtoWrapper(
    val data: LinkOutputDto,
)

fun List<LinkEntity>.toWrapperDto(): LinkListOutputDtoWrapper {
    val dataList = this.map { it.toLinkDto() }
    return LinkListOutputDtoWrapper(data = dataList)
}

fun LinkEntity.toWrapperDto() = SingleLinkOutputDtoWrapper(
    data = this.toLinkDto()
)

// endregion
