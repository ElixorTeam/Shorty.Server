package ru.elixor.api.features.domain.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elixor.api.entities.domain.DomainRepository
import ru.elixor.api.features.domain.dto.*

@Service
class DomainServiceImpl(private val domainRepository: DomainRepository) : DomainService {
    override fun getAll(): DomainsOutputDtoWrapper = domainRepository.findAll().toWrapperDto()

    @Transactional
    override fun create(dto: DomainCreateDto): DomainOutputDto {
        val domain = dto.toEntity()
        domainRepository.save(domain)
        return domain.toDto()
    }
}