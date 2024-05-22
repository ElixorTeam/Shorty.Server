package ru.elixor.api.features.user.features.tag.common

import ru.elixor.api.features.user.features.tag.dto.TagOutputDto
import ru.elixor.api.features.user.features.tag.dto.TagUpdateDto
import ru.elixor.api.features.user.features.tag.dto.TagsOutputDtoWrapper
import java.util.*

interface TagService {
    // region Queries

    fun getAll(userUid: UUID): TagsOutputDtoWrapper

    // endregion

    // region CRUD

    fun update(title: String, userUid: UUID, dto: TagUpdateDto): TagOutputDto
    fun delete(title: String, userUid: UUID)

    // endregion
}