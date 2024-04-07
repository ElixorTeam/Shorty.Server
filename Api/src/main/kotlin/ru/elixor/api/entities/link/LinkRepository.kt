package ru.elixor.api.entities.link

import org.springframework.data.jpa.repository.JpaRepository
import ru.elixor.api.entities.domain.DomainEntity
import ru.elixor.api.entities.sub.domain.SubDomainEntity
import java.util.*

interface LinkRepository : JpaRepository<LinkEntity, UUID> {
    fun findLinkEntityByUidAndUserUid(uid: UUID, userUid: UUID): LinkEntity?
    fun findAllByUserUid(userUid: UUID): List<LinkEntity>
    fun existsByDomainAndSubdomainAndPath(domain: DomainEntity, subdomain: SubDomainEntity?, path: String): Boolean
}