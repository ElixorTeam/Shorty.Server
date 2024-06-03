package ru.elixor.api.features.user.features.link.dto

import ru.elixor.api.entities.link.LinkEntity
import java.util.*


// region LinkDto

data class LinkDto(
    val uid: UUID,
    val title: String,
    val path: String,
    val urls: MutableSet<String>,
    val isEnable: Boolean,
    val tags: MutableSet<String>,
    val domainUid: UUID,
    val subdomainUid: UUID?,
    val password: String?,
    val updateDt: Date,
    val createDt: Date
)

private fun LinkEntity.toLinkDto() = LinkDto(
    uid = uid,
    title = title,
    urls = urls.map { it.url.toString() }.toHashSet(),
    path = path,
    isEnable = isEnable,
    tags = tags.map { it.value }.toHashSet(),
    subdomainUid = subdomain?.uid,
    domainUid = domain.uid,
    createDt = createDt,
    updateDt = updateDt,
    password = password,
)

// endregion

// region OutputDto

data class LinkOutputDto(
    val data: LinkDto,
)

fun LinkEntity.toDto() = LinkOutputDto(
    data = toLinkDto()
)

// endregion

// region OutputDto Wrapper

data class LinksOutputDtoWrapper(
    val data: List<LinkDto>,
)

fun List<LinkEntity>.toWrapperDto(): LinksOutputDtoWrapper {
    val dataList = this.map { it.toLinkDto() }
    return LinksOutputDtoWrapper(data = dataList)
}

// endregion