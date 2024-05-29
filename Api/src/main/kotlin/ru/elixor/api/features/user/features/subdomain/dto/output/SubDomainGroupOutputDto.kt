package ru.elixor.api.features.user.features.subdomain.dto.output

import ru.elixor.api.entities.subdomain.SubDomainEntity
import java.util.*

//region SubDomainGroup

class SubDomainGroupItem(
    val uid: UUID,
    val value: String
)

class SubDomainGroup(
    val domainUid: UUID,
    val domainValue: String,
    val subdomains: List<SubDomainGroupItem>
)

//endregion

// region SubDomainGroupDto

data class SubDomainGroupOutputDto(
    val data: List<SubDomainGroup>,
)

fun List<SubDomainEntity>.toGroupDto(): SubDomainGroupOutputDto {
    val domainToSubDomainsMap: Map<Pair<UUID, String>, List<SubDomainEntity>> = this.groupBy {
        it.domain.uid to it.domain.value
    }
    val subDomainGroups = mutableListOf<SubDomainGroup>()

    domainToSubDomainsMap.forEach { (key, subDomains) ->
        val (uid, value) = key
        val subdomainsDto = subDomains.map { SubDomainGroupItem(it.uid, it.value) }.toMutableList()
        subDomainGroups.add(SubDomainGroup(uid, value, subdomainsDto))
    }
    return SubDomainGroupOutputDto(subDomainGroups)
}

// endregion