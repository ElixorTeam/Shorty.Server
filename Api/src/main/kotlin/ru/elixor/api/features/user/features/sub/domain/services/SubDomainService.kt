package ru.elixor.api.features.user.features.sub.domain.services

import ru.elixor.api.features.user.features.sub.domain.dto.SubDomainOutputDtoWrapper
import java.util.*

interface SubDomainService {
    // region Queries

    fun getAllByDomainUid(userUid: UUID, domainUid: UUID): SubDomainOutputDtoWrapper

    // endregion

    // region Commands

//    fun delete(linkId: UUID, userUid: UUID)
//    fun create(linkCreateDto: LinkCreateDto, userUid: UUID): LinkOutputDto

    // endregion
}