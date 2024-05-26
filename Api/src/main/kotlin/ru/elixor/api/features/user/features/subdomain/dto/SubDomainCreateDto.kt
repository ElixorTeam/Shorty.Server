package ru.elixor.api.features.user.features.subdomain.dto

import jakarta.validation.constraints.NotNull
import ru.elixor.api.utils.validators.subdomain.ValidSubDomain
import java.util.*

class SubDomainCreateDto(
    @field:ValidSubDomain
    val value: String,

    @field:NotNull
    val domainUid: UUID
)