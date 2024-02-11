package ru.elixor.api.features.link

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.elixor.api.features.link.dto.LinkCreateDto
import ru.elixor.api.features.link.dto.LinkOutputDto
import ru.elixor.api.features.link.dto.LinkUpdateDto
import java.util.*

@RestController
@RequestMapping("/api/v1/links")
class LinkController(private val linkService: LinkService) {

    // region Queries
    @GetMapping
    fun getAll(@AuthenticationPrincipal jwt: Jwt): List<LinkOutputDto> = linkService.getAll(jwt)

    // endregion

    // region Commands

    @PostMapping
    fun create(@RequestBody @Validated dto: LinkCreateDto, @AuthenticationPrincipal jwt: Jwt):
            LinkOutputDto = linkService.create(dto, jwt)

    @PutMapping("/{id}")
    fun update(@Validated @PathVariable id: UUID, @RequestBody @Validated dto: LinkUpdateDto, @AuthenticationPrincipal jwt: Jwt):
            LinkOutputDto = linkService.update(id, dto, jwt)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID, @AuthenticationPrincipal jwt: Jwt) = linkService.delete(id, jwt)

    // endregion
}