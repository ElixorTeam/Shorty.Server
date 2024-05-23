package ru.elixor.api.entities.link

import org.springframework.data.jpa.repository.JpaRepository
import ru.elixor.api.entities.domain.DomainEntity
import ru.elixor.api.entities.subdomain.SubDomainEntity
import java.util.*

interface LinkRepository : JpaRepository<LinkEntity, UUID> {
    // region FindBy

    fun findByUidAndUserUid(uid: UUID, userUid: UUID): Optional<LinkEntity>
    fun findByDomainValueAndSubdomainValueAndPath(domain: String, subdomain: String?, path: String): Optional<LinkEntity>
    fun findAllByUserUid(userUid: UUID): List<LinkEntity>

    // endregion

    // region ExistsBy

    fun existsBySubdomain(subDomain: SubDomainEntity): Boolean
    fun existsByDomainAndSubdomainAndPath(domain: DomainEntity, subdomain: SubDomainEntity?, path: String): Boolean

    // endregion
}