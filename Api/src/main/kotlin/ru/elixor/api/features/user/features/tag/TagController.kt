package ru.elixor.api.features.user.features.tag

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.elixor.api.configurations.security.annotations.UserUid
import ru.elixor.api.features.user.features.tag.common.TagService
import ru.elixor.api.features.user.features.tag.dto.TagsOutputDtoWrapper
import java.util.*

@RestController
@RequestMapping("/api/v1/user/tags")
class TagController(private val tagService: TagService) {

    @GetMapping
    fun getAll(@UserUid userUid: UUID): TagsOutputDtoWrapper = tagService.getAll(userUid)

}