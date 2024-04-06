package ru.elixor.api.features.tag.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

class TagUpdateDto(
    @field:NotNull
    @field:Size(min = 2, max = 16, message = "Title must be [2, 156] characters")
    val title: String
)