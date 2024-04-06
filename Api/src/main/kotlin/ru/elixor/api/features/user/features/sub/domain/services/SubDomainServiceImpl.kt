package ru.elixor.api.features.user.features.sub.domain.services

import org.springframework.stereotype.Service
import ru.elixor.api.entities.sub.domain.SubDomainRepository
import ru.elixor.api.features.user.features.sub.domain.dto.SubDomainOutputDtoWrapper
import ru.elixor.api.features.user.features.sub.domain.dto.toWrapperDto
import java.util.*

@Service
class SubDomainServiceImpl(private val subDomainRepo: SubDomainRepository) : SubDomainService {

    override fun getAllByDomainUid(userUid: UUID, domainUid: UUID) : SubDomainOutputDtoWrapper =
        subDomainRepo.findAllByUserUidAndDomain_Uid(userUid, domainUid).toWrapperDto();
}