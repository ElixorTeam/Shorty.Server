package ru.elixor.api.features.user.features.tag.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elixor.api.entities.tag.TagEntity
import ru.elixor.api.entities.tag.TagRepository
import ru.elixor.api.exceptions.errors.UniqueConflictException
import ru.elixor.api.features.user.features.tag.common.TagService
import ru.elixor.api.features.user.features.tag.dto.*
import java.util.*


@Service
class TagServiceImpl(
    private val tagRepo: TagRepository,
    private val tagHelper: TagServiceHelper
) : TagService {
    // region Queries

    override fun getAll(userUid: UUID): TagsOutputDtoWrapper =
        tagRepo.findAllByUserUid(userUid).toWrapperDto()

    // endregion

    // region CRUD

    @Transactional
    override fun update(title: String, userUid: UUID, dto: TagUpdateDto): TagOutputDto {
        if (tagRepo.existsByUserUidAndTitle(userUid, title))
            throw UniqueConflictException()

        val tag: TagEntity = tagHelper.getByTitleAndUser(userUid, title).apply {
            this.title = dto.title;
        }
        return tagRepo.save(tag).toDto()
    }

    @Transactional
    override fun delete(title: String, userUid: UUID) {
        val tag: TagEntity = tagHelper.getByTitleAndUser(userUid, title)
        val links = tag.links.toList()

        for (link in links)
            link.tags.remove(tag)
        tagRepo.delete(tag)
    }

    // endregion
}