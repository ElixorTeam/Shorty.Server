package ru.elixor.api.features.admin.features.domain.common

import ru.elixor.api.features.admin.features.domain.dto.DomainCreateDto
import ru.elixor.api.features.admin.features.domain.dto.DomainOutputDto
import ru.elixor.api.features.admin.features.domain.dto.DomainsOutputDtoWrapper
import java.util.*

interface DomainService {
    // region Queries

    fun getAll(): DomainsOutputDtoWrapper

    // endregion

    // region CRUD

    fun create(dto: DomainCreateDto): DomainOutputDto
    fun delete(domainId: UUID)

    // endregion
}