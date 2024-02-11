package ru.elixor.api.features.link.services

import org.springframework.security.oauth2.jwt.Jwt
import ru.elixor.api.features.link.LinkService
import ru.elixor.api.features.link.dto.LinkCreateDto
import ru.elixor.api.features.link.dto.LinkOutputDto
import ru.elixor.api.features.link.dto.LinkUpdateDto
import java.util.*

class LinkServiceImpl : LinkService {

    // region Queries
    override fun getAll(jwt: Jwt): List<LinkOutputDto> {
        TODO("Not yet implemented")
    }

    override fun getLinkById(id: UUID, jwt: Jwt): LinkOutputDto {
        TODO("Not yet implemented")
    }

    // endregion

    // region Commands

    override fun create(linkCreateDto: LinkCreateDto, jwt: Jwt): LinkOutputDto {
        TODO("Not yet implemented")
    }

    override fun update(linkId: UUID, linkUpdateDto: LinkUpdateDto, jwt: Jwt): LinkOutputDto {
        TODO("Not yet implemented")
    }

    override fun delete(linkId: UUID, jwt: Jwt) {
        TODO("Not yet implemented")
    }

    // endregion
}