package ru.elixor.api.features.link.services

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elixor.api.entities.domain.DomainEntity
import ru.elixor.api.entities.domain.DomainRepository
import ru.elixor.api.entities.link.LinkEntity
import ru.elixor.api.entities.link.LinkRepository
import ru.elixor.api.entities.tag.TagEntity
import ru.elixor.api.entities.tag.TagRepository
import ru.elixor.api.exceptions.errors.NotFoundByIdException
import ru.elixor.api.features.link.dto.*
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Service
class LinkServiceImpl(
    private val linkRepository: LinkRepository,
    private val tagRepository: TagRepository,
    private val domainRepository: DomainRepository,
    @PersistenceContext private val entityManager: EntityManager
) : LinkService {
    // region Queries

    override fun getAll(userUid: UUID):
            LinksOutputDtoWrapper = linkRepository.findAllByUserUid(userUid).toWrapperDto();

    override fun getLinkById(linkId: UUID, userUid: UUID):
            LinkOutputDto = getLinkByIdAndUser(linkId, userUid).toDto()

    // endregion

    // region Commands

    @Transactional
    override fun create(linkCreateDto: LinkCreateDto, userUid: UUID): LinkOutputDto {
        val domain: DomainEntity = domainRepository.findById(linkCreateDto.domainUid).getOrNull() ?:
            throw NotFoundByIdException(linkCreateDto.domainUid.toString(), "domain")

        val linkExists: Boolean = linkRepository.existsByDomainAndSubdomain(domain, linkCreateDto.subdomain)
        if (linkExists) throw NoSuchElementException("Link exists")

        var link = linkCreateDto.toEntity()
        link.tags = saveTagsIfNotExist(linkCreateDto.tags, userUid)
        link.domain = domain
        link.userUid = userUid

        link = linkRepository.save(link);
        entityManager.flush()

        return link.toDto()
    }

    @Transactional
    override fun update(linkId: UUID, linkUpdateDto: LinkUpdateDto, userUid: UUID): LinkOutputDto {
        var link: LinkEntity = getLinkByIdAndUser(linkId, userUid)
        link.title = linkUpdateDto.title;
        link.password = linkUpdateDto.password;
        link.tags = saveTagsIfNotExist(linkUpdateDto.tags, userUid)

        link = linkRepository.save(link);
        entityManager.flush()

        return link.toDto()
    }

    @Transactional
    override fun delete(linkId: UUID, userUid: UUID) {
        val link: LinkEntity = getLinkByIdAndUser(linkId, userUid)
        linkRepository.delete(link)
    }

    // endregion

    // region Private

    private fun getLinkByIdAndUser(linkId: UUID, userUid: UUID): LinkEntity =
        linkRepository.findLinkEntityByUidAndUserUid(linkId, userUid) ?: throw NotFoundByIdException(linkId.toString(), "link")

    private fun saveTagsIfNotExist(tagNames: MutableSet<String>, userUid: UUID): MutableSet<TagEntity> {
        val existingTags = tagRepository.findAllByUserUid(userUid)
        val filledTags = mutableSetOf<TagEntity>()
        val newTags = mutableSetOf<TagEntity>()

        for (tagName in tagNames) {
            val existingTag = existingTags.find { it.title == tagName }
            if (existingTag == null) {
                val newTag = TagEntity()
                newTag.title = tagName
                newTag.userUid = userUid
                newTags.add(newTag)
            } else {
                filledTags.add(existingTag)
            }
        }

        if (newTags.isNotEmpty()) {
            tagRepository.saveAll(newTags)
        }

        return (newTags + filledTags).toMutableSet()
    }
    // endregion
}