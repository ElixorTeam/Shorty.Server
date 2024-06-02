package ru.elixor.api.features.user.features.tag.common

import ru.elixor.api.features.user.features.tag.dto.TagsOutputDtoWrapper
import java.util.*

interface TagService {
    fun getAll(userUid: UUID): TagsOutputDtoWrapper
}