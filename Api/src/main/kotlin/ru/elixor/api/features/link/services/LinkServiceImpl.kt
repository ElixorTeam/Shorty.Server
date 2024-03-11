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

@Service
class LinkServiceImpl(private val linkRepository: LinkRepository,
                      private val domainRepository: DomainRepository) : LinkService {
    // region Queries

    override fun getAll(userUid: UUID): List<LinkOutputDto> {
        return linkRepository.findAllByUserUid(userUid).map { it.toDto() }
    }

    override fun getLinkById(linkId: UUID, userUid: UUID): LinkOutputDto {
        val link: LinkEntity = getLinkByIdAndUser(linkId, userUid)
        return link.toDto()
    }

    // endregion

    // region Commands

    override fun create(linkCreateDto: LinkCreateDto, userUid: UUID): LinkOutputDto {
        val domain: DomainEntity = domainRepository.findById(linkCreateDto.domainUid).get();
        val linkExists: Boolean = linkRepository.existsByDomainAndSubdomain(domain, linkCreateDto.subdomain)
        if (linkExists) throw NoSuchElementException("Link exists")

        val link = linkCreateDto.toEntity()

        link.domain = domain
        link.userUid = userUid

        linkRepository.save(link)

        return link.toDto()
    }

    override fun update(linkId: UUID, linkUpdateDto: LinkUpdateDto, userUid: UUID): LinkOutputDto {
        val link: LinkEntity = getLinkByIdAndUser(linkId, userUid)
        link.title = linkUpdateDto.title;
        link.password = linkUpdateDto.password;
        linkRepository.save(link)
        return link.toDto()
    }

    @Transactional
    override fun delete(linkId: UUID, userUid: UUID) {
        val link: LinkEntity = getLinkByIdAndUser(linkId, userUid)
        linkRepository.delete(link)
    }

    // endregion

    // region Private

    private fun getLinkByIdAndUser(linkId: UUID, userUid: UUID): LinkEntity {
        return linkRepository.findFirstByUidAndUserUid(linkId, userUid)
            ?: throw NotFoundByUidException(linkId)
    }

    // endregion
}