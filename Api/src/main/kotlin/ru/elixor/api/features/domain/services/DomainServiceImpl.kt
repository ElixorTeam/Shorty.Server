package ru.elixor.api.features.domain.services

import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elixor.api.entities.domain.DomainEntity
import ru.elixor.api.entities.domain.DomainRepository
import ru.elixor.api.features.domain.dto.DomainCreateDto
import ru.elixor.api.features.domain.dto.DomainOutputDto
import ru.elixor.api.features.domain.dto.toDto

@Service
class DomainServiceImpl(private val domainRepository: DomainRepository) : DomainService {

    override fun getAll(jwt: Jwt): List<DomainOutputDto> {
        return domainRepository.findAll().map { it.toDto() }
    }

    @Transactional
    override fun create(domainCreateDto: DomainCreateDto, jwt: Jwt): DomainOutputDto {
        val domain = DomainEntity()
        domain.value = domainCreateDto.title
        domainRepository.save(domain)
        return domain.toDto()
    }
}