package ru.elixor.api.features.domain.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elixor.api.entities.domain.DomainRepository
import ru.elixor.api.exceptions.errors.DbConflictException
import ru.elixor.api.features.domain.common.DomainService
import ru.elixor.api.features.domain.dto.*

@Service
class DomainServiceImpl(private val domainRepo: DomainRepository) : DomainService {
    // region Queries

    override fun getAll(): DomainsOutputDtoWrapper = domainRepo.findAll().toWrapperDto()

    // endregion

    // region CRUD

    @Transactional
    override fun create(dto: DomainCreateDto): DomainOutputDto {
        if (domainRepo.existsByValue(dto.value))
            throw DbConflictException()

        val domain = dto.toEntity()
        domainRepo.save(domain)
        return domain.toDto()
    }

    // endregion
}