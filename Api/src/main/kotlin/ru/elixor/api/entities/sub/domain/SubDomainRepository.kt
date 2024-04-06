package ru.elixor.api.entities.sub.domain

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SubDomainRepository  : JpaRepository<SubDomainEntity, UUID> {
    fun findAllByUserUidAndDomain_Uid(uid: UUID, domainUid: UUID): List<SubDomainEntity>
    fun existsByUserUidAndValue(uid: UUID, value: String): Boolean
}