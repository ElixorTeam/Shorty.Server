package ru.elixor.api.features.user.features.link.services

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

    // region Commands

    fun delete(linkId: UUID, userUid: UUID)
    fun create(linkCreateDto: LinkCreateDto, userUid: UUID): LinkOutputDto
    fun update(linkId: UUID, linkUpdateDto: LinkUpdateDto, userUid: UUID): LinkOutputDto

    // endregion
}