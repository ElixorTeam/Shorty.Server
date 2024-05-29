package ru.elixor.api.features.admin.features.domain.dto

import ru.elixor.api.entities.domain.DomainEntity
import java.util.*


// region Misc

data class DomainDto(
    val uid: UUID,
    val value: String,
)

private fun DomainEntity.toDomainDto() = DomainDto(
    uid = uid,
    value = value,
)

// endregion

// region OutputDto

data class DomainOutputDto(
    val data: DomainDto,
)

fun DomainEntity.toDto() = DomainOutputDto(
    data = toDomainDto()
)

// endregion

// region OutputDto Wrapper

data class DomainsOutputDtoWrapper(
    val data: List<DomainDto>,
)

fun List<DomainEntity>.toWrapperDto(): DomainsOutputDtoWrapper {
    val dataList = this.map { it.toDomainDto() }
    return DomainsOutputDtoWrapper(data = dataList)
}

// endregion