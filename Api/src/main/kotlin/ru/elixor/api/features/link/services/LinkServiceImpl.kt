package ru.elixor.api.features.link.services

import org.springframework.security.oauth2.jwt.Jwt
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

    override fun getAll(jwt: Jwt): List<LinkOutputDto> {
        return linkRepository.findAllByUserUid(UUID.fromString(jwt.subject)).map { it.toDto() }
    }

    override fun getLinkById(linkId: UUID, jwt: Jwt): LinkOutputDto {
        val link: LinkEntity = getLinkByIdAndUser(linkId, jwt)
        return link.toDto()
    }

    // endregion

    // region Commands

    override fun create(linkCreateDto: LinkCreateDto, jwt: Jwt): LinkOutputDto {
        val domain: DomainEntity = domainRepository.findById(linkCreateDto.domainUid).get();
        val linkExists: Boolean = linkRepository.existsByDomainAndSubdomain(domain, linkCreateDto.subdomain)
        if (linkExists) throw NoSuchElementException("Link exists")

        val link = linkCreateDto.toEntity()
        link.domain = domain;
        link.userUid = UUID.fromString(jwt.subject)

        linkRepository.save(link)

        return link.toDto()
    }

    override fun update(linkId: UUID, linkUpdateDto: LinkUpdateDto, jwt: Jwt): LinkOutputDto {
        val link: LinkEntity = getLinkByIdAndUser(linkId, jwt)
        link.title = linkUpdateDto.title;
        link.password = linkUpdateDto.password;
        linkRepository.save(link)
        return link.toDto()
    }

    @Transactional
    override fun delete(linkId: UUID, jwt: Jwt) {
        val link: LinkEntity = getLinkByIdAndUser(linkId, jwt)
        linkRepository.delete(link)
    }

    // endregion

    // region Private
    private fun getLinkByIdAndUser(linkId: UUID, jwt: Jwt): LinkEntity {
        val link = linkRepository.findFirstByUidAndUserUid(linkId, UUID.fromString(jwt.subject))
            ?: throw NotFoundByUidException(linkId)
        return link;
    }

    // endregion
}