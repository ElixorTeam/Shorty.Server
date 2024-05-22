package ru.elixor.api.features.user.features.tag

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.elixor.api.configurations.security.annotations.UserUid
import ru.elixor.api.features.user.features.tag.common.TagService
import ru.elixor.api.features.user.features.tag.dto.TagOutputDto
import ru.elixor.api.features.user.features.tag.dto.TagUpdateDto
import ru.elixor.api.features.user.features.tag.dto.TagsOutputDtoWrapper
import java.util.*

@RestController
@RequestMapping("/api/v1/user/tags")
class TagController(private val tagService: TagService) {
    // region Queries

    @GetMapping
    fun getAll(@UserUid userUid: UUID): TagsOutputDtoWrapper = tagService.getAll(userUid)

    // endregion

    // region CRUD

    @PutMapping("/{title}")
    fun update(@PathVariable title: String, @UserUid userUid: UUID, @RequestBody @Validated dto: TagUpdateDto):
            TagOutputDto = tagService.update(title, userUid, dto)

    @DeleteMapping("/{title}")
    fun delete(@PathVariable title: String, @UserUid userUid: UUID) = tagService.delete(title, userUid)

    // endregion
}