package ru.elixor.api.features.tag

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.elixor.api.features.tag.dto.TagOutputDto
import ru.elixor.api.features.tag.dto.TagOutputDtoWrapper
import ru.elixor.api.features.tag.services.TagService

@RestController
@RequestMapping("/api/v1/tags")
class TagController(private val tagService: TagService)
{
    // region Queries
    @GetMapping
    fun getAll(@AuthenticationPrincipal jwt: Jwt): TagOutputDtoWrapper = tagService.getAll(jwt)

    // endregion

    // region Commands

    // endregion
}