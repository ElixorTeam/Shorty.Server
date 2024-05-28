package ru.elixor.api.features.domain.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elixor.api.entities.domain.DomainEntity
import ru.elixor.api.entities.domain.DomainRepository
import ru.elixor.api.entities.link.LinkRepository
import ru.elixor.api.entities.subdomain.SubDomainRepository
import ru.elixor.api.exceptions.errors.DbConflictException
import ru.elixor.api.features.domain.common.DomainService
import ru.elixor.api.features.domain.dto.*
import java.util.*

@Service
class DomainServiceImpl(
    private val domainHelper: DomainServiceHelper,
    private val domainRepo: DomainRepository,
    private val subDomainRepo: SubDomainRepository,
    private val linkRepo: LinkRepository
) : DomainService {
    // region Queries

    override fun getAll(): DomainsOutputDtoWrapper = domainRepo.findAll().toWrapperDto()

    // endregion

    // region CRUD

    @Transactional
    override fun create(dto: DomainCreateDto): DomainOutputDto {

        if (domainRepo.existsByValue(dto.value) || domainRepo.count() >= 5)
            throw DbConflictException()

        val domain = dto.toEntity()
        domainRepo.save(domain)
        return domain.toDto()
    }

    @Transactional
    override fun delete(domainId: UUID) {
        val domain: DomainEntity = domainHelper.getByUid(domainId)
        if (subDomainRepo.existsByDomain(domain) || linkRepo.existsByDomain(domain))
            throw DbConflictException()
        domainRepo.delete(domain)
    }

    // endregion
}