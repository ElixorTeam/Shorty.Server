package ru.elixor.api.features.user.features.sub.domain.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elixor.api.entities.domain.DomainEntity
import ru.elixor.api.entities.domain.DomainRepository
import ru.elixor.api.entities.sub.domain.SubDomainEntity
import ru.elixor.api.entities.sub.domain.SubDomainRepository
import ru.elixor.api.exceptions.errors.NotFoundByIdException
import ru.elixor.api.exceptions.errors.UniqueConflictException
import ru.elixor.api.features.user.features.sub.domain.dto.*
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Service
class SubDomainServiceImpl(
    private val subDomainRepo: SubDomainRepository,
    private val domainRepo: DomainRepository
) : SubDomainService {

    override fun getAllByDomainUid(userUid: UUID, domainUid: UUID) : SubDomainOutputDtoWrapper =
        subDomainRepo.findAllByUserUidAndDomain_Uid(userUid, domainUid).toWrapperDto();

    @Transactional
    override fun create(dto: SubDomainCreateDto, userUid: UUID): SubDomainOutputDto {
        val domain: DomainEntity = domainRepo.findById(dto.domainUid).getOrNull() ?: throw NotFoundByIdException(
            dto.domainUid.toString(),
            "domain"
        )

        val subdomainExists: Boolean = subDomainRepo.existsByUserUidAndValue(userUid, dto.value)
        if (subdomainExists) throw UniqueConflictException()

        val subdomain = SubDomainEntity()
        subdomain.userUid = userUid
        subdomain.domain = domain
        subdomain.value = dto.value

        subDomainRepo.save(subdomain);
        return subdomain.toDto()
    }
}