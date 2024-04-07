package ru.elixor.api.features.domain.services

import ru.elixor.api.features.domain.dto.DomainCreateDto
import ru.elixor.api.features.domain.dto.DomainOutputDto
import ru.elixor.api.features.domain.dto.DomainsOutputDtoWrapper

interface DomainService {
    fun getAll(): DomainsOutputDtoWrapper
    fun create(dto: DomainCreateDto): DomainOutputDto
}