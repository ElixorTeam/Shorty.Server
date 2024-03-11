package ru.elixor.api.features.link.services

import ru.elixor.api.features.link.dto.LinkCreateDto
import ru.elixor.api.features.link.dto.LinkOutputDto
import ru.elixor.api.features.link.dto.LinkUpdateDto
import java.util.*

interface LinkService {
    // region Queries

    fun getAll(userUid: UUID): List<LinkOutputDto>
    fun getLinkById(linkId: UUID, userUid: UUID): LinkOutputDto

    // endregion

    // region Commands

    fun create(linkCreateDto: LinkCreateDto, userUid: UUID): LinkOutputDto
    fun update(linkId: UUID, linkUpdateDto: LinkUpdateDto, userUid: UUID): LinkOutputDto
    fun delete(linkId: UUID, userUid: UUID)

    // endregion
}