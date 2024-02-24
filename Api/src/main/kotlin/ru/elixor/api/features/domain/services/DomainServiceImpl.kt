package ru.elixor.api.features.domain.services

import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Service
import ru.elixor.api.features.domain.dto.DomainCreateDto
import ru.elixor.api.features.domain.dto.DomainOutputDto

@Service
class DomainServiceImpl : DomainService {

    override fun getAll(jwt: Jwt): List<DomainOutputDto> {
        TODO("Not yet implemented")
    }

    override fun create(domainCreateDto: DomainCreateDto, jwt: Jwt): DomainOutputDto {
        TODO("Not yet implemented")
    }
}