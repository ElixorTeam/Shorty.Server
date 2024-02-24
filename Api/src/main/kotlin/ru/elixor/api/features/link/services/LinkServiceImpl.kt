package ru.elixor.api.features.link.services

import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Service
import ru.elixor.api.features.link.dto.LinkCreateDto
import ru.elixor.api.features.link.dto.LinkOutputDto
import ru.elixor.api.features.link.dto.LinkUpdateDto
import java.time.LocalDateTime
import java.util.*

@Service
class LinkServiceImpl : LinkService {
    // region Queries

    override fun getAll(jwt: Jwt): List<LinkOutputDto> {
        return listOf(
            LinkOutputDto(UUID.randomUUID(), "CreatedLink", "shorty", "admin", LocalDateTime.now(), LocalDateTime.now()),
            LinkOutputDto(UUID.randomUUID(), "CreatedLink", "shorty", "vk", LocalDateTime.now(), LocalDateTime.now()),
            LinkOutputDto(UUID.randomUUID(), "CreatedLink", "shorty", "youtube", LocalDateTime.now(), LocalDateTime.now()),
            LinkOutputDto(UUID.randomUUID(), "CreatedLink", "shorty", "telegram", LocalDateTime.now(), LocalDateTime.now())
        )
    }

    override fun getLinkById(id: UUID, jwt: Jwt): LinkOutputDto {
       return LinkOutputDto(id, "CreatedLink", "shorty", "telegram", LocalDateTime.now(), LocalDateTime.now())
    }

    // endregion

    // region Commands

    override fun create(linkCreateDto: LinkCreateDto, jwt: Jwt): LinkOutputDto {
        return LinkOutputDto(UUID.randomUUID(), "CreatedLink", "shorty", "admin", LocalDateTime.now(), LocalDateTime.now())
    }

    override fun update(linkId: UUID, linkUpdateDto: LinkUpdateDto, jwt: Jwt): LinkOutputDto {
        return LinkOutputDto(linkId, "UpdatedLink", "shorty", "telegram", LocalDateTime.now(), LocalDateTime.now())
    }

    override fun delete(linkId: UUID, jwt: Jwt) {
    }

    // endregion
}