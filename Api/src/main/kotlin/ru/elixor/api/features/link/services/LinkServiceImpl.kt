package ru.elixor.api.features.link.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elixor.api.entities.domain.DomainEntity
import ru.elixor.api.entities.domain.DomainRepository
import ru.elixor.api.entities.link.LinkEntity
import ru.elixor.api.entities.link.LinkRepository
import ru.elixor.api.exceptions.errors.NotFoundByUidException
import ru.elixor.api.features.link.dto.*
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Service
class LinkServiceImpl(private val linkRepository: LinkRepository,
                      private val domainRepository: DomainRepository) : LinkService {
    // region Queries

    override fun getAll(userUid: UUID):
            LinkListOutputDtoWrapper = linkRepository.findAllByUserUid(userUid).toWrapperDto();

    override fun getLinkById(linkId: UUID, userUid: UUID):
            SingleLinkOutputDtoWrapper = getLinkByIdAndUser(linkId, userUid).toWrapperDto()

    // endregion

    // region Commands

    override fun create(linkCreateDto: LinkCreateDto, userUid: UUID): SingleLinkOutputDtoWrapper {
        val domain: DomainEntity = domainRepository.findById(linkCreateDto.domainUid).getOrNull() ?:
            throw NotFoundByUidException(linkCreateDto.domainUid, "domain")

        val linkExists: Boolean = linkRepository.existsByDomainAndSubdomain(domain, linkCreateDto.subdomain)
        if (linkExists) throw NoSuchElementException("Link exists")

        val link = linkCreateDto.toEntity()
        link.domain = domain
        link.userUid = userUid
        return linkRepository.save(link).toWrapperDto()
    }

    override fun update(linkId: UUID, linkUpdateDto: LinkUpdateDto, userUid: UUID): SingleLinkOutputDtoWrapper {
        val link: LinkEntity = getLinkByIdAndUser(linkId, userUid)
        link.title = linkUpdateDto.title;
        link.password = linkUpdateDto.password;
        return linkRepository.save(link).toWrapperDto()
    }

    @Transactional
    override fun delete(linkId: UUID, userUid: UUID) {
        val link: LinkEntity = getLinkByIdAndUser(linkId, userUid)
        linkRepository.delete(link)
    }

    // endregion

    // region Private

    private fun getLinkByIdAndUser(linkId: UUID, userUid: UUID): LinkEntity =
        linkRepository.findLinkEntityByUidAndUserUid(linkId, userUid) ?: throw NotFoundByUidException(linkId, "link")

    // endregion
}