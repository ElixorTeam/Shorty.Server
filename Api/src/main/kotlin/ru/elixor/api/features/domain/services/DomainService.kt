package ru.elixor.api.features.domain.services

import org.springframework.security.oauth2.jwt.Jwt
import ru.elixor.api.features.domain.dto.DomainCreateDto
import ru.elixor.api.features.domain.dto.DomainOutputDto
import ru.elixor.api.features.domain.dto.DomainsOutputDtoWrapper

interface DomainService {
    fun getAll(jwt: Jwt): DomainsOutputDtoWrapper
    fun create(domainCreateDto: DomainCreateDto, jwt: Jwt): DomainOutputDto
}