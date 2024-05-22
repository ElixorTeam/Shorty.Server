package ru.elixor.api.features.user.features.subdomain.dto

import ru.elixor.api.entities.subdomain.SubDomainEntity
import java.util.*

//region SubDomainDto

class SubDomainDto(
    val uid: UUID,
    val value: String
)

private fun SubDomainEntity.toSubDomainDto() = SubDomainDto(
    uid = uid,
    value = value
)

//endregion

// region OutputDto

data class SubDomainOutputDto(
    val data: SubDomainDto,
)

fun SubDomainEntity.toDto() = SubDomainOutputDto(
    data = toSubDomainDto()
)

// endregion

// region OutputDtoWrapper

data class SubDomainOutputDtoWrapper(
    val data: List<SubDomainDto>,
)

fun List<SubDomainEntity>.toWrapperDto(): SubDomainOutputDtoWrapper {
    val dataList = this.map { it.toSubDomainDto() }
    return SubDomainOutputDtoWrapper(data = dataList)
}

// endregion