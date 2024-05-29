package ru.elixor.api.features.user.features.subdomain.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elixor.api.entities.domain.DomainEntity
import ru.elixor.api.entities.domain.DomainRepository
import ru.elixor.api.entities.link.LinkRepository
import ru.elixor.api.entities.subdomain.SubDomainEntity
import ru.elixor.api.entities.subdomain.SubDomainRepository
import ru.elixor.api.exceptions.errors.FkNotFoundException
import ru.elixor.api.exceptions.errors.TooManyRecordsException
import ru.elixor.api.exceptions.errors.UniqueConflictException
import ru.elixor.api.features.user.features.subdomain.common.SubDomainService
import ru.elixor.api.features.user.features.subdomain.dto.SubDomainCreateDto
import ru.elixor.api.features.user.features.subdomain.dto.output.*
import java.util.*

@Service
class SubDomainServiceImpl(
    private val subDomainHelper: SubDomainServiceHelper,
    private val subDomainRepo: SubDomainRepository,
    private val domainRepo: DomainRepository,
    private val linkRepo: LinkRepository
) : SubDomainService {

    // region Queries

    override fun getAll(userUid: UUID): SubDomainGroupOutputDto {
        val domains = domainRepo.findAll()
        val subdomainsMap = subDomainRepo.findAllByUserUid(userUid).groupBy { it.domain.uid }

        val subDomainGroups = domains.map { domain ->
            val subDomainEntities = subdomainsMap[domain.uid] ?: emptyList()
            val subDomainItems = subDomainEntities.map { subDomainEntity ->
                SubDomainGroupItem(
                    uid = subDomainEntity.uid,
                    value = subDomainEntity.value
                )
            }
            SubDomainGroup(domain.uid, domain.value, subDomainItems)
        }

        return SubDomainGroupOutputDto(subDomainGroups)
    }

    // endregion

    // region CRUD

    @Transactional
    override fun create(dto: SubDomainCreateDto, userUid: UUID): SubDomainOutputDto {
        val maxRecords = 3

        val domain: DomainEntity = domainRepo.findById(dto.domainUid).orElseThrow {
            FkNotFoundException()
        }

        if (subDomainRepo.countByDomainAndUserUid(domain, userUid) >= maxRecords)
            throw TooManyRecordsException(maxRecords)

        if (subDomainRepo.existsByUserUidAndValue(userUid, dto.value)) {
            throw UniqueConflictException()
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
            throw UniqueConflictException()

        subDomainRepo.delete(subDomain)
    }

    // endregion
}