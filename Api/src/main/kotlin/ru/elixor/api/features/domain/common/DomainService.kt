package ru.elixor.api.features.domain.common

import ru.elixor.api.features.domain.dto.DomainCreateDto
import ru.elixor.api.features.domain.dto.DomainOutputDto
import ru.elixor.api.features.domain.dto.DomainsOutputDtoWrapper

interface DomainService {
    // region Queries

    fun getAll(): DomainsOutputDtoWrapper

    // endregion

    // region CRUD

    fun create(dto: DomainCreateDto): DomainOutputDto

    // endregion
}