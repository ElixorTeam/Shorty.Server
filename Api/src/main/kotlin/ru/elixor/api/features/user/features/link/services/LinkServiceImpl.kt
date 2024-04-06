package ru.elixor.api.features.user.features.link.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elixor.api.entities.domain.DomainEntity
import ru.elixor.api.entities.domain.DomainRepository
import ru.elixor.api.entities.link.LinkEntity
import ru.elixor.api.entities.link.LinkRepository
import ru.elixor.api.entities.tag.TagEntity
import ru.elixor.api.entities.tag.TagRepository
import ru.elixor.api.exceptions.errors.NotFoundByIdException
import ru.elixor.api.exceptions.errors.UniqueConflictException
import ru.elixor.api.features.user.features.link.dto.*
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Service
class LinkServiceImpl(
    private val linkRepository: LinkRepository,
    private val tagRepository: TagRepository,
    private val domainRepository: DomainRepository
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
        if (linkExists) throw UniqueConflictException()

        var link = linkCreateDto.toEntity()
        link.tags = saveTagsIfNotExist(linkCreateDto.tags, userUid)
        link.domain = domain
        link.userUid = userUid

        link = linkRepository.saveAndFlush(link);
        return link.toDto()
    }

    @Transactional
    override fun update(linkId: UUID, linkUpdateDto: LinkUpdateDto, userUid: UUID): LinkOutputDto {
        var link: LinkEntity = getLinkByIdAndUser(linkId, userUid)
        link.title = linkUpdateDto.title;
        link.password = linkUpdateDto.password;
        link.tags = saveTagsIfNotExist(linkUpdateDto.tags, userUid)

        link = linkRepository.saveAndFlush(link);
        tagRepository.deleteUnused(userUid)
        return link.toDto()
    }

    @Transactional
    override fun delete(linkId: UUID, userUid: UUID) {
        val link: LinkEntity = getLinkByIdAndUser(linkId, userUid)
        link.tags.clear()
        tagRepository.deleteUnused(userUid)
        linkRepository.delete(link)
    }

    // endregion

    // region Private

    private fun getLinkByIdAndUser(linkId: UUID, userUid: UUID): LinkEntity =
        linkRepository.findLinkEntityByUidAndUserUid(linkId, userUid) ?: throw NotFoundByIdException(linkId.toString(), "link")

    private fun saveTagsIfNotExist(tagNames: MutableSet<String>, userUid: UUID): MutableSet<TagEntity> {
        val existingTags: List<TagEntity> = tagRepository.findAllByUserUidAndTitleIn(userUid, tagNames)
        val newTagsName = tagNames - existingTags.map { it.title }.toSet()
        val tagsToSave = newTagsName.map {
            TagEntity().apply {
                title = it
                this.userUid = userUid
            }
        }
        return (tagRepository.saveAll(tagsToSave) + existingTags).toMutableSet()
    }
    // endregion
}