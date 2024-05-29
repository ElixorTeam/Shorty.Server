package ru.elixor.api.features.user.features.subdomain.dto.output

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

// endregion