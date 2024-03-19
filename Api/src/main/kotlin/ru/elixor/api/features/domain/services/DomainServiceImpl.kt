package ru.elixor.api.features.domain.services

import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elixor.api.entities.domain.DomainRepository
import ru.elixor.api.features.domain.dto.*

@Service
class DomainServiceImpl(private val domainRepository: DomainRepository) : DomainService {

    override fun getAll(jwt: Jwt): DomainOutputDtoWrapper {
        return domainRepository.findAll().toWrapperDto()
    }

    @Transactional
    override fun create(domainCreateDto: DomainCreateDto, jwt: Jwt): DomainOutputDto {
        val domain = domainCreateDto.toEntity()
        domainRepository.save(domain)
        return domain.toDto()
    }
}