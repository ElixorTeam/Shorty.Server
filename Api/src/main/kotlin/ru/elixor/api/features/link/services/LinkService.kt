package ru.elixor.api.features.link.services

import ru.elixor.api.features.link.dto.*
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