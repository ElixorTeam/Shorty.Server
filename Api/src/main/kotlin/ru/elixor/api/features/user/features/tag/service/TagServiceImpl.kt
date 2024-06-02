package ru.elixor.api.features.user.features.tag.service

import org.springframework.stereotype.Service
import ru.elixor.api.entities.tag.TagRepository
import ru.elixor.api.features.user.features.tag.common.TagService
import ru.elixor.api.features.user.features.tag.dto.TagsOutputDtoWrapper
import ru.elixor.api.features.user.features.tag.dto.toWrapperDto
import java.util.*


@Service
class TagServiceImpl(
    private val tagRepo: TagRepository,
) : TagService {
    // region Queries

    override fun getAll(userUid: UUID): TagsOutputDtoWrapper =
        tagRepo.findAllByUserUid(userUid).toWrapperDto()

    // endregion
}