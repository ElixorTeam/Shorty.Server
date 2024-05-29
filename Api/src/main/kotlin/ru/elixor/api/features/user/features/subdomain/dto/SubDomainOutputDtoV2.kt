package ru.elixor.api.features.user.features.subdomain.dto

import ru.elixor.api.entities.subdomain.SubDomainEntity
import java.util.*

//region SubDomainDto

class DomainDto(
    val uid: UUID,
    val value: String,
    val subdomains: Set<SubDomainDto>
)

private fun SubDomainEntity.toSubDomainDto() = SubDomainDto(
    uid = uid,
    value = value
)

//endregion

// region OutputDto

data class SubDomainOutputDtoV2(
    val data: List<DomainDto>,
)

// endregion

// region OutputDtoWrapper

data class SubDomainOutputDtoV2Wrapper(
    val data: List<DomainDto>,
)

// endregion