package ru.elixor.api.features.domain.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elixor.api.entities.domain.DomainRepository
import ru.elixor.api.exceptions.errors.UniqueConflictException
import ru.elixor.api.features.domain.dto.*

@Service
class DomainServiceImpl(private val domainRepo: DomainRepository) : DomainService {
    override fun getAll(): DomainsOutputDtoWrapper = domainRepo.findAll().toWrapperDto()

    @Transactional
    override fun create(dto: DomainCreateDto): DomainOutputDto {
        val domainExists: Boolean = domainRepo.existsByValue(dto.value)
        if (domainExists) throw UniqueConflictException()

        val domain = dto.toEntity()
        domainRepo.save(domain)
        return domain.toDto()
    }
}