package ru.elixor.api.features.user.features.subdomain.dto.output

import ru.elixor.api.entities.subdomain.SubDomainEntity
import java.util.*

class SubDomainItem(
    val uid: UUID,
    val value: String,
    val domainUid: UUID
)

data class SubDomainOutputDto(
    val data: SubDomainItem
)

fun SubDomainEntity.toDto() = SubDomainOutputDto(
    data = SubDomainItem(uid, value, domain.uid)
)