package ru.elixor.api.features.user.features.subdomain.common

import ru.elixor.api.features.user.features.subdomain.dto.SubDomainCreateDto
import ru.elixor.api.features.user.features.subdomain.dto.SubDomainOutputDto
import ru.elixor.api.features.user.features.subdomain.dto.SubDomainOutputDtoV2Wrapper
import ru.elixor.api.features.user.features.subdomain.dto.SubDomainOutputDtoWrapper
import java.util.*

interface SubDomainService {
    // region Queries

    fun getAll(userUid: UUID): SubDomainOutputDtoV2Wrapper
    fun getAllByDomainUid(userUid: UUID, domainUid: UUID): SubDomainOutputDtoWrapper

    // endregion

    // region CRUD

    fun create(dto: SubDomainCreateDto, userUid: UUID): SubDomainOutputDto

    fun delete(subdomainId: UUID, userUid: UUID)

    // endregion
}