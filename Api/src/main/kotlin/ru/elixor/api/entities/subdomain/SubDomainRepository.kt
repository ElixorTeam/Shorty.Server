package ru.elixor.api.entities.subdomain

import org.springframework.data.jpa.repository.JpaRepository
import ru.elixor.api.entities.domain.DomainEntity
import java.util.*

interface SubDomainRepository : JpaRepository<SubDomainEntity, UUID> {
    fun findByUidAndUserUid(uid: UUID, userUid: UUID): Optional<SubDomainEntity>
    fun findAllByUserUid(userUid: UUID): List<SubDomainEntity>
    fun existsByUserUidAndValue(userUid: UUID, value: String): Boolean
    fun existsByDomain(domain : DomainEntity) : Boolean
    fun countByDomainAndUserUid(domain : DomainEntity, userUid: UUID) : Int
}