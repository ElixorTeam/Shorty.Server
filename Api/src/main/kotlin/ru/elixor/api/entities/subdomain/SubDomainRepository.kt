package ru.elixor.api.entities.subdomain

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SubDomainRepository : JpaRepository<SubDomainEntity, UUID> {
    fun findByUidAndUserUid(uid: UUID, userUid: UUID): Optional<SubDomainEntity>
    fun findAllByUserUidAndDomainUid(userUid: UUID, domainUid: UUID): List<SubDomainEntity>
    fun existsByUserUidAndValue(userUid: UUID, value: String): Boolean
}