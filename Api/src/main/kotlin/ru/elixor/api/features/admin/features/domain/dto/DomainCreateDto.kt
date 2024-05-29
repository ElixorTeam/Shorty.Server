package ru.elixor.api.features.admin.features.domain.dto

import ru.elixor.api.entities.domain.DomainEntity
import ru.elixor.api.utils.validators.ValidDomain

class DomainCreateDto(
    @field:ValidDomain
    val value: String
)


fun DomainCreateDto.toEntity(): DomainEntity {
    val domain = DomainEntity()
    domain.value = value
    return domain
}