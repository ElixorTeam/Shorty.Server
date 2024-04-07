package ru.elixor.api.features.user.features.sub.domain.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.util.*

class SubDomainCreateDto(
    @field:NotNull
    @field:Size(min = 2, max = 16, message = "value must be [2, 16] characters")
    @field:Pattern(regexp = "^[a-zA-Z]{2,16}\$", message = "value must contain only Latin characters")
    val value: String,

    @field:NotNull
    val domainUid: UUID
)