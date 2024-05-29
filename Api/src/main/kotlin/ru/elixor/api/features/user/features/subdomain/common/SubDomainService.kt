package ru.elixor.api.features.user.features.subdomain.common

import ru.elixor.api.features.user.features.subdomain.dto.SubDomainCreateDto
import ru.elixor.api.features.user.features.subdomain.dto.output.SubDomainGroupOutputDto
import ru.elixor.api.features.user.features.subdomain.dto.output.SubDomainOutputDto
import java.util.*

interface SubDomainService {
    // region Queries

    fun getAll(userUid: UUID): SubDomainGroupOutputDto

    // endregion

    // region CRUD

    fun create(dto: SubDomainCreateDto, userUid: UUID): SubDomainOutputDto

    fun delete(subdomainId: UUID, userUid: UUID)

    // endregion
}