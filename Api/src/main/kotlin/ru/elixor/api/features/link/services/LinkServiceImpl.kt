package ru.elixor.api.features.link.services

import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elixor.api.entities.link.LinkEntity
import ru.elixor.api.entities.link.LinkRepository
import ru.elixor.api.features.link.dto.LinkCreateDto
import ru.elixor.api.features.link.dto.LinkOutputDto
import ru.elixor.api.features.link.dto.LinkUpdateDto
import ru.elixor.api.features.link.dto.toDto
import java.time.LocalDateTime
import java.util.*

@Service
class LinkServiceImpl(private val linkRepository: LinkRepository) : LinkService {
    // region Queries

    override fun getAll(jwt: Jwt): List<LinkOutputDto> {
        return linkRepository.findAllByUserUid(UUID.fromString(jwt.subject)).map { it.toDto() }
    }

    override fun getLinkById(linkId: UUID, jwt: Jwt): LinkOutputDto {
        val link: LinkEntity = getLinkByUser(linkId, jwt)
        return link.toDto()
    }

    // endregion

    // region Commands

    override fun create(linkCreateDto: LinkCreateDto, jwt: Jwt): LinkOutputDto {
        return LinkOutputDto(UUID.randomUUID(), "CreatedLink", "shorty", "admin", LocalDateTime.now(), LocalDateTime.now())
    }

    override fun update(linkId: UUID, linkUpdateDto: LinkUpdateDto, jwt: Jwt): LinkOutputDto {
        val link: LinkEntity = getLinkByUser(linkId, jwt)
        linkRepository.save(link)
        return link.toDto()
    }

    @Transactional
    override fun delete(linkId: UUID, jwt: Jwt) {
        val link: LinkEntity = getLinkByUser(linkId, jwt)
        linkRepository.delete(link)
    }

    // endregion

    // region Private
    private fun getLinkByUser(linkId: UUID, jwt: Jwt): LinkEntity {
        val link = linkRepository.findFirstByUidAndUserUid(linkId, UUID.fromString(jwt.subject))
            ?: throw NoSuchElementException("Link not found")
        return link;
    }

    // endregion
}