package ru.elixor.api.features.user.features.tag.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import ru.elixor.api.entities.tag.TagEntity
import ru.elixor.api.entities.tag.TagRepository
import ru.elixor.api.exceptions.errors.NotFoundException
import java.util.*

@Service
class TagServiceHelper(private val tagRepo: TagRepository) {

    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    fun getByTitleAndUser(userUid: UUID, title: String): TagEntity =
        tagRepo.findFirstByUserUidAndTitle(userUid, title).orElseThrow { NotFoundException() }

}