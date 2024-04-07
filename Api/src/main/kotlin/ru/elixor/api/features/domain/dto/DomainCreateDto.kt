package ru.elixor.api.features.domain.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import ru.elixor.api.entities.domain.DomainEntity

class DomainCreateDto(
    @field:NotNull
    @field:Size(min = 5, max = 32, message = "value must be [5, 32] characters")
    @field:Pattern(regexp = "^[a-zA-Z0-9.]{5,32}\$", message = "value must contain only Latin characters, digits, and one dot")
    val value: String
)


fun DomainCreateDto.toEntity(): DomainEntity {
    val domain = DomainEntity()
    domain.value = value
    return domain;
}