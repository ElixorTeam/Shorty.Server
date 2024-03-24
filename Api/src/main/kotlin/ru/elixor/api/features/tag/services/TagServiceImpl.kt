package ru.elixor.api.features.tag.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elixor.api.entities.tag.TagEntity
import ru.elixor.api.entities.tag.TagRepository
import ru.elixor.api.exceptions.errors.NotFoundByIdException
import ru.elixor.api.exceptions.errors.UniqueConflictException
import ru.elixor.api.features.tag.dto.*
import java.util.*


@Service
class TagServiceImpl(private val tagRepository: TagRepository) : TagService {
    override fun getAll(userUid: UUID):
            TagsOutputDtoWrapper = tagRepository.findAllByUserUid(userUid).toWrapperDto()

    @Transactional
    override fun update(title: String, userUid: UUID, tagUpdateDto: TagUpdateDto): TagOutputDto {
        val renameTagExists: TagEntity? = tagRepository.findFirstByUserUidAndTitle(userUid, tagUpdateDto.title)
        if (renameTagExists != null) throw UniqueConflictException()

        val tag: TagEntity = getTagByTitleAndUser(title, userUid)
        tag.title = tagUpdateDto.title
        return tagRepository.save(tag).toDto()
    }

    @Transactional
    override fun delete(title: String, userUid: UUID) {
        val tag: TagEntity = getTagByTitleAndUser(title, userUid)
        tagRepository.delete(tag)
    }

    private fun getTagByTitleAndUser(title: String, userUid: UUID): TagEntity =
        tagRepository.findFirstByUserUidAndTitle(userUid, title) ?: throw NotFoundByIdException(title, "tag")
}