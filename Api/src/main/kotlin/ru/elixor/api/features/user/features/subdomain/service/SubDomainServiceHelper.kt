package ru.elixor.api.features.user.features.subdomain.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import ru.elixor.api.entities.subdomain.SubDomainEntity
import ru.elixor.api.entities.subdomain.SubDomainRepository
import ru.elixor.api.exceptions.errors.NotFoundException
import java.util.*

@Service
class SubDomainServiceHelper(private val subDomainRepo: SubDomainRepository) {

    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    fun getByIdAndUser(subdomainId: UUID, userUid: UUID): SubDomainEntity =
        subDomainRepo.findByUidAndUserUid(subdomainId, userUid).orElseThrow {
            NotFoundException()
        }

}