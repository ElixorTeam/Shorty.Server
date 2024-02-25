package ru.elixor.api.features.domain.services

import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elixor.api.entities.category.CategoryRepository
import ru.elixor.api.entities.domain.DomainRepository
import ru.elixor.api.features.domain.dto.DomainCreateDto
import ru.elixor.api.features.domain.dto.DomainOutputDto

@Service
class DomainServiceImpl(private val domainRepository: DomainRepository) : DomainService {

    override fun getAll(jwt: Jwt): List<DomainOutputDto> {
        domainRepository.findAll()
        return listOf()
    }

    @Transactional
    override fun create(domainCreateDto: DomainCreateDto, jwt: Jwt): DomainOutputDto {
        TODO("Not yet implemented")
    }
}