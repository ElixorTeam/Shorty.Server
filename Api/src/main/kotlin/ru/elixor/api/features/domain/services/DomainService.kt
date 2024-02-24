package ru.elixor.api.features.domain.services

import org.springframework.security.oauth2.jwt.Jwt
import ru.elixor.api.features.domain.dto.DomainCreateDto
import ru.elixor.api.features.domain.dto.DomainOutputDto

interface DomainService {
    fun getAll(jwt: Jwt): List<DomainOutputDto>
    fun create(domainCreateDto: DomainCreateDto, jwt: Jwt): DomainOutputDto
}