package ru.elixor.api.features.user.features.sub.domain.services

import ru.elixor.api.features.user.features.sub.domain.dto.SubDomainCreateDto
import ru.elixor.api.features.user.features.sub.domain.dto.SubDomainOutputDto
import ru.elixor.api.features.user.features.sub.domain.dto.SubDomainOutputDtoWrapper
import java.util.*

interface SubDomainService {
    // region Queries

    fun getAllByDomainUid(userUid: UUID, domainUid: UUID): SubDomainOutputDtoWrapper

    // endregion

    // region Commands

    fun create(dto: SubDomainCreateDto, userUid: UUID): SubDomainOutputDto

    fun delete(subdomainId: UUID, userUid: UUID)

    // endregion
}