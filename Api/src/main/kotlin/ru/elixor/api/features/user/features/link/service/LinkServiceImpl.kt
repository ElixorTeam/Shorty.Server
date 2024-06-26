package ru.elixor.api.features.user.features.link.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elixor.api.entities.domain.DomainEntity
import ru.elixor.api.entities.domain.DomainRepository
import ru.elixor.api.entities.link.LinkEntity
import ru.elixor.api.entities.link.LinkRepository
import ru.elixor.api.entities.subdomain.SubDomainEntity
import ru.elixor.api.entities.subdomain.SubDomainRepository
import ru.elixor.api.entities.tag.TagRepository
import ru.elixor.api.entities.url.UrlEntity
import ru.elixor.api.exceptions.errors.DataNotSyncException
import ru.elixor.api.exceptions.errors.FkNotFoundException
import ru.elixor.api.exceptions.errors.UniqueConflictException
import ru.elixor.api.features.user.features.link.common.LinkService
import ru.elixor.api.features.user.features.link.dto.*
import java.net.URL
import java.util.*

@Service
class LinkServiceImpl(
    private val linkRepo: LinkRepository,
    private val tagRepo: TagRepository,
    private val domainRepo: DomainRepository,
    private val subDomainRepo: SubDomainRepository,
    private val linkHelper: LinkServiceHelper
) : LinkService {
    // region Queries

    @Transactional(readOnly = true)
    override fun getAll(userUid: UUID):
            LinksOutputDtoWrapper = linkRepo.findAllByUserUid(userUid).toWrapperDto();

    @Transactional(readOnly = true)
    override fun getLinkById(linkId: UUID, userUid: UUID):
            LinkOutputDto = linkHelper.getByIdAndUser(linkId, userUid).toDto()

    // endregion

    // region CRUD

    @Transactional
    override fun create(dto: LinkCreateDto, userUid: UUID): LinkOutputDto {
        val domain: DomainEntity = domainRepo.findById(dto.domainUid)
            .orElseThrow { FkNotFoundException() }

        val urlDomains = domainRepo.findAll().map { it.value.lowercase(Locale.getDefault()) }

        dto.urls.forEach { url ->
            if (urlDomains.any { domain -> url.lowercase(Locale.getDefault()).contains(domain) }) {
                throw DataNotSyncException()
            }
        }

        val subDomain: SubDomainEntity? = dto.subdomainUid?.let { subdomainUid ->
            subDomainRepo.findById(subdomainUid)
                .filter { it.domain.uid == domain.uid }
                .orElseThrow {
                    throw DataNotSyncException()
                }
        }

        if (linkRepo.existsByDomainAndSubdomainAndPath(domain, subDomain, dto.path)) {
            throw UniqueConflictException()
        }

        val link = dto.toEntity().apply {
            tags = linkHelper.saveTagsIfNotExist(dto.tags, userUid)
            this.domain = domain
            this.subdomain = subDomain
            this.userUid = userUid
        }
        link.urls = dto.urls.map { urlString ->
            UrlEntity().apply {
                url = URL(urlString)
                this.link = link
            }
        }.toMutableSet()
        return linkRepo.saveAndFlush(link).toDto()
    }

    @Transactional
    override fun update(linkId: UUID, dto: LinkUpdateDto, userUid: UUID): LinkOutputDto {
        var link: LinkEntity = linkHelper.getByIdAndUser(linkId, userUid).apply {
            title = dto.title
            password = dto.password
            isEnable = dto.isEnable
            tags = linkHelper.saveTagsIfNotExist(dto.tags, userUid)
        }
        link = linkRepo.saveAndFlush(link);
        tagRepo.deleteUnused(userUid)
        return link.toDto()
    }

    @Transactional
    override fun delete(linkId: UUID, userUid: UUID) {
        val link: LinkEntity = linkHelper.getByIdAndUser(linkId, userUid)
        link.tags.clear()
        tagRepo.deleteUnused(userUid)
        linkRepo.delete(link)
    }

    // endregion
}