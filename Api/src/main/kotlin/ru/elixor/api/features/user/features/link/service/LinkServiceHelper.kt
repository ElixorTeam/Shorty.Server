package ru.elixor.api.features.user.features.link.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import ru.elixor.api.entities.link.LinkEntity
import ru.elixor.api.entities.link.LinkRepository
import ru.elixor.api.entities.tag.TagEntity
import ru.elixor.api.entities.tag.TagRepository
import ru.elixor.api.exceptions.errors.NotFoundException
import java.util.*

@Service
class LinkServiceHelper(
    private val linkRepo: LinkRepository,
    private val tagRepo: TagRepository,
) {

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    fun getByIdAndUser(linkId: UUID, userUid: UUID): LinkEntity =
        linkRepo.findByUidAndUserUid(linkId, userUid).orElseThrow {
            NotFoundException()
        }

    @Transactional(propagation = Propagation.REQUIRED)
    fun saveTagsIfNotExist(tagNames: MutableSet<String>, userUid: UUID): MutableSet<TagEntity> {
        val existingTags: List<TagEntity> = tagRepo.findAllByUserUidAndValueIn(userUid, tagNames)
        val newTagsName = tagNames - existingTags.map { it.value }.toSet()
        val tagsToSave = newTagsName.map {
            TagEntity().apply {
                value = it
                this.userUid = userUid
            }
        }
        return (tagRepo.saveAll(tagsToSave) + existingTags).toMutableSet()
    }
}