package ru.elixor.api.features.tag.services

import org.springframework.security.oauth2.jwt.Jwt
import ru.elixor.api.features.tag.dto.TagOutputDto
import ru.elixor.api.features.tag.dto.TagUpdateDto
import java.util.*

interface TagService {
    fun getAll(jwt: Jwt): List<TagOutputDto>
    fun update(linkId: UUID, tagUpdateDto: TagUpdateDto, jwt: Jwt): TagOutputDto
}