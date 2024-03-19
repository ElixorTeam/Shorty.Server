package ru.elixor.api.features.domain.dto

import ru.elixor.api.entities.domain.DomainEntity
import java.util.*

class DomainOutputDto (
    val uid: UUID,
    val value: String,
)

fun DomainEntity.toDto() = DomainOutputDto(
    uid = uid!!,
    value = value!!,
)


class DomainOutputDtoWrapper(
    val data: List<DomainOutputDto>
)

fun List<DomainEntity>.toWrapperDto(): DomainOutputDtoWrapper {
    val data = this.map { it.toDto() }
    return DomainOutputDtoWrapper(data);
}
