package ru.elixor.api.features.tag.dto

import ru.elixor.api.entities.tag.TagEntity
import java.util.*

class TagOutputDto (
    val uid: UUID,
    val value: String,
)

fun TagEntity.toDto() = TagOutputDto(
    uid = uid,
    value = title
)

class TagOutputDtoWrapper(
    val data: List<TagOutputDto>
)


fun List<TagEntity>.toWrapperDto(): TagOutputDtoWrapper {
    val data = this.map { it.toDto() }
    return TagOutputDtoWrapper(data);
}
