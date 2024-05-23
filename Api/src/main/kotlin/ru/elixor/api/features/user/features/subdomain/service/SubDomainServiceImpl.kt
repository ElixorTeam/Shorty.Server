package ru.elixor.api.features.user.features.subdomain.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elixor.api.entities.domain.DomainEntity
import ru.elixor.api.entities.domain.DomainRepository
import ru.elixor.api.entities.link.LinkRepository
import ru.elixor.api.entities.subdomain.SubDomainEntity
import ru.elixor.api.entities.subdomain.SubDomainRepository
import ru.elixor.api.exceptions.errors.DbConflictException
import ru.elixor.api.exceptions.errors.NotFoundException
import ru.elixor.api.features.user.features.subdomain.common.SubDomainService
import ru.elixor.api.features.user.features.subdomain.dto.*
import java.util.*

@Service
class SubDomainServiceImpl(
    private val subDomainHelper: SubDomainServiceHelper,
    private val subDomainRepo: SubDomainRepository,
    private val domainRepo: DomainRepository,
    private val linkRepo: LinkRepository
) : SubDomainService {
    // region Queries

    override fun getAllByDomainUid(userUid: UUID, domainUid: UUID): SubDomainOutputDtoWrapper =
        subDomainRepo.findAllByUserUidAndDomainUid(userUid, domainUid).toWrapperDto();

    // endregion

    // region CRUD

    @Transactional
    override fun create(dto: SubDomainCreateDto, userUid: UUID): SubDomainOutputDto {
        val domain: DomainEntity = domainRepo.findById(dto.domainUid).orElseThrow {
            NotFoundException()
        }

        if (subDomainRepo.existsByUserUidAndValue(userUid, dto.value)) {
            throw DbConflictException()
        }

        val subdomain = SubDomainEntity().apply {
            this.userUid = userUid
            this.domain = domain
            this.value = dto.value
        }

        subDomainRepo.save(subdomain);
        return subdomain.toDto()
    }

    override fun delete(subdomainId: UUID, userUid: UUID) {
        val subDomain: SubDomainEntity = subDomainHelper.getByIdAndUser(subdomainId, userUid)

        if (linkRepo.existsBySubdomain(subDomain))
            throw DbConflictException()

        subDomainRepo.delete(subDomain)
    }

    // endregion
}