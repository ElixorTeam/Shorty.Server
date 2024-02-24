package ru.elixor.api.features.link.services

import org.springframework.security.oauth2.jwt.Jwt
import ru.elixor.api.features.link.dto.LinkCreateDto
import ru.elixor.api.features.link.dto.LinkOutputDto
import ru.elixor.api.features.link.dto.LinkUpdateDto
import java.util.UUID

interface LinkService {
    // region Queries

    fun getAll(jwt: Jwt): List<LinkOutputDto>
    fun getLinkById(id: UUID, jwt: Jwt): LinkOutputDto

    // endregion

    // region Commands

    fun create(linkCreateDto: LinkCreateDto, jwt: Jwt): LinkOutputDto
    fun update(linkId: UUID, linkUpdateDto: LinkUpdateDto, jwt: Jwt): LinkOutputDto
    fun delete(linkId: UUID, jwt: Jwt)

    // endregion
}