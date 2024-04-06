package ru.elixor.api.features.domain.dto

import ru.elixor.api.entities.domain.DomainEntity

class DomainCreateDto(
    val value: String
)


fun DomainCreateDto.toEntity(): DomainEntity {
    val domain = DomainEntity()
    domain.value = value
    return domain;
}