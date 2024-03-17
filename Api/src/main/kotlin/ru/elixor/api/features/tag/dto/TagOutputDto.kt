package ru.elixor.api.features.tag.dto

import ru.elixor.api.entities.tag.TagEntity
import java.util.*

class TagOutputDto (
    val uid: UUID,
    val title: String,
)

fun TagEntity.toDto() = TagOutputDto(
    uid = uid!!,
    title = title
)