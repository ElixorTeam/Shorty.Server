package ru.elixor.api.features.user.features.link

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.elixor.api.configurations.security.annotations.UserUid
import ru.elixor.api.features.user.features.link.common.LinkService
import ru.elixor.api.features.user.features.link.dto.LinkCreateDto
import ru.elixor.api.features.user.features.link.dto.LinkOutputDto
import ru.elixor.api.features.user.features.link.dto.LinkUpdateDto
import ru.elixor.api.features.user.features.link.dto.LinksOutputDtoWrapper
import java.util.*

@RestController
@RequestMapping("/api/v1/user/links")
class LinkController(private val linkService: LinkService) {
    // region Queries

    @GetMapping("/{id}")
    fun get(@PathVariable id: UUID, @UserUid userUid: UUID) = linkService.getLinkById(id, userUid)

    @GetMapping
    fun getAll(@UserUid userUid: UUID): LinksOutputDtoWrapper = linkService.getAll(userUid)

    // endregion

    // region CRUD

    @PostMapping
    fun create(@RequestBody @Validated dto: LinkCreateDto, @UserUid userUid: UUID):
            LinkOutputDto = linkService.create(dto, userUid)

    @PutMapping("/{id}")
    fun update(@PathVariable id: UUID, @UserUid userUid: UUID, @RequestBody @Validated dto: LinkUpdateDto):
            LinkOutputDto = linkService.update(id, dto, userUid)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID, @UserUid userUid: UUID) = linkService.delete(id, userUid)

    // endregion
}