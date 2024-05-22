package ru.elixor.api.features.user.features.link.common

import ru.elixor.api.features.user.features.link.dto.LinkCreateDto
import ru.elixor.api.features.user.features.link.dto.LinkOutputDto
import ru.elixor.api.features.user.features.link.dto.LinkUpdateDto
import ru.elixor.api.features.user.features.link.dto.LinksOutputDtoWrapper
import java.util.*

interface LinkService {
    // region Queries

    fun getAll(userUid: UUID): LinksOutputDtoWrapper
    fun getLinkById(linkId: UUID, userUid: UUID): LinkOutputDto

    // endregion

    // region CRUD

    fun delete(linkId: UUID, userUid: UUID)
    fun create(dto: LinkCreateDto, userUid: UUID): LinkOutputDto
    fun update(linkId: UUID, dto: LinkUpdateDto, userUid: UUID): LinkOutputDto

    // endregion
}