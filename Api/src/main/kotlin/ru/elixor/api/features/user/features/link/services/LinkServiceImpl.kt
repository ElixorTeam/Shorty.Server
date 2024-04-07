package ru.elixor.api.features.user.features.link.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elixor.api.entities.domain.DomainEntity
import ru.elixor.api.entities.domain.DomainRepository
import ru.elixor.api.entities.link.LinkEntity
import ru.elixor.api.entities.link.LinkRepository
import ru.elixor.api.entities.sub.domain.SubDomainEntity
import ru.elixor.api.entities.sub.domain.SubDomainRepository
import ru.elixor.api.entities.tag.TagEntity
import ru.elixor.api.entities.tag.TagRepository
import ru.elixor.api.exceptions.errors.NotFoundByIdException
import ru.elixor.api.exceptions.errors.UniqueConflictException
import ru.elixor.api.features.user.features.link.dto.*
import java.util.*

@Service
class LinkServiceImpl(
    private val linkRepo: LinkRepository,
    private val tagRepo: TagRepository,
    private val domainRepo: DomainRepository,
    private val subDomainRepo: SubDomainRepository
) : LinkService {
    // region Queries

    override fun getAll(userUid: UUID):
            LinksOutputDtoWrapper = linkRepo.findAllByUserUid(userUid).toWrapperDto();

    override fun getLinkById(linkId: UUID, userUid: UUID):
            LinkOutputDto = getLinkByIdAndUser(linkId, userUid).toDto()

    // endregion

    // region Commands

    @Transactional
    override fun create(dto: LinkCreateDto, userUid: UUID): LinkOutputDto {
        val domain: DomainEntity = domainRepo.findById(dto.domainUid)
            .orElseThrow { NotFoundByIdException(dto.domainUid.toString(), "domain") }

        val subDomain: SubDomainEntity? = dto.subdomainUid?.let { subdomainUid ->
            subDomainRepo.findById(subdomainUid)
                .filter { it.domain.uid == domain.uid }
                .orElseThrow { NotFoundByIdException(subdomainUid.toString(), "subDomain") }
        }

        if (linkRepo.existsByDomainAndSubdomainAndPath(domain, subDomain, dto.path)) {
            throw UniqueConflictException()
        }

        val link = dto.toEntity().apply {
            tags = saveTagsIfNotExist(dto.tags, userUid)
            this.domain = domain
            this.subdomain = subDomain
            this.userUid = userUid
        }
        return linkRepo.saveAndFlush(link).toDto()
    }

    @Transactional
    override fun update(linkId: UUID, dto: LinkUpdateDto, userUid: UUID): LinkOutputDto {
        var link: LinkEntity = getLinkByIdAndUser(linkId, userUid)
        link.title = dto.title;
        link.password = dto.password;
        link.tags = saveTagsIfNotExist(dto.tags, userUid)

        link = linkRepo.saveAndFlush(link);
        tagRepo.deleteUnused(userUid)
        return link.toDto()
    }

    @Transactional
    override fun delete(linkId: UUID, userUid: UUID) {
        val link: LinkEntity = getLinkByIdAndUser(linkId, userUid)
        link.tags.clear()
        tagRepo.deleteUnused(userUid)
        linkRepo.delete(link)
    }

    // endregion

    // region Private

    private fun getLinkByIdAndUser(linkId: UUID, userUid: UUID): LinkEntity =
        linkRepo.findLinkEntityByUidAndUserUid(linkId, userUid) ?: throw NotFoundByIdException(
            linkId.toString(),
            "link"
        )

    private fun saveTagsIfNotExist(tagNames: MutableSet<String>, userUid: UUID): MutableSet<TagEntity> {
        val existingTags: List<TagEntity> = tagRepo.findAllByUserUidAndTitleIn(userUid, tagNames)
        val newTagsName = tagNames - existingTags.map { it.title }.toSet()
        val tagsToSave = newTagsName.map {
            TagEntity().apply {
                title = it
                this.userUid = userUid
            }
        }
        return (tagRepo.saveAll(tagsToSave) + existingTags).toMutableSet()
    }
    // endregion
}