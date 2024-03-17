package ru.elixor.api.features.link.services

import ru.elixor.api.features.link.dto.*
import java.util.*

interface LinkService {
    // region Queries

    fun getAll(userUid: UUID): LinkListOutputDtoWrapper
    fun getLinkById(linkId: UUID, userUid: UUID): SingleLinkOutputDtoWrapper

    // endregion

    // region Commands

    fun delete(linkId: UUID, userUid: UUID)
    fun create(linkCreateDto: LinkCreateDto, userUid: UUID): SingleLinkOutputDtoWrapper
    fun update(linkId: UUID, linkUpdateDto: LinkUpdateDto, userUid: UUID): SingleLinkOutputDtoWrapper

    // endregion
}