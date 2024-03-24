package ru.elixor.api.features.tag.services

import ru.elixor.api.features.tag.dto.TagOutputDto
import ru.elixor.api.features.tag.dto.TagUpdateDto
import ru.elixor.api.features.tag.dto.TagsOutputDtoWrapper
import java.util.*

interface TagService {
    // region Queries

    fun getAll(userUid: UUID): TagsOutputDtoWrapper

    // endregion

    // region Commands
    fun update(title: String, userUid: UUID, tagUpdateDto: TagUpdateDto): TagOutputDto
    fun delete(title: String, userUid: UUID)

    // endregion
}