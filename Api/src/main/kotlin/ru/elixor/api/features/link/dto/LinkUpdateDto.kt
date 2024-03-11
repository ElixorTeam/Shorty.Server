package ru.elixor.api.features.link.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class LinkUpdateDto(

    @field:NotNull
    @field:Size(min = 2, max = 64, message = "Title must be [2, 64] characters")
    val title: String,

    @field:Size(min = 2, max = 16, message = "Password must be [2, 16] characters")
    val password: String?,
)