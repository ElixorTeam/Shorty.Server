package ru.elixor.api.features.user.features.tag.dto

import ru.elixor.api.entities.tag.TagEntity
import java.util.*

//region TagDto

data class TagDto (
    val uid: UUID,
    val value: String
)

private fun TagEntity.toTagDto() = TagDto(
    uid = uid,
    value = title
)


//endregion

// region OutputDto

data class TagOutputDto(
    val data: TagDto
)

fun TagEntity.toDto() = TagOutputDto(
    data = toTagDto()
)

//endregion

// region OutputDto Wrapper

data class TagsOutputDtoWrapper(
    val data: List<TagDto>
)

fun List<TagEntity>.toWrapperDto(): TagsOutputDtoWrapper {
    val data = this.map { it.toTagDto() }
    return TagsOutputDtoWrapper(data)
}

// endregion