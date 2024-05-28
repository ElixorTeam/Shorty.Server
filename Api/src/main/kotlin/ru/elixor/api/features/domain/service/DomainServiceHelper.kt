package ru.elixor.api.features.domain.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import ru.elixor.api.entities.domain.DomainEntity
import ru.elixor.api.entities.domain.DomainRepository
import ru.elixor.api.exceptions.errors.NotFoundException
import java.util.*

@Service
class DomainServiceHelper(private val domainRepo: DomainRepository) {

    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    fun getByUid(domainUid: UUID): DomainEntity =
        domainRepo.findById(domainUid).orElseThrow { NotFoundException() }

}