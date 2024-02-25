package ru.elixor.api.features.folder

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.elixor.api.features.folder.dto.FolderOutputDto
import ru.elixor.api.features.folder.services.FolderService

@RestController
@RequestMapping("/api/v1/folders")
class FolderController(private val folderService: FolderService)
{
    // region Queries
    @GetMapping
    fun getAll(@AuthenticationPrincipal jwt: Jwt): List<FolderOutputDto> = folderService.getAll(jwt)

    // endregion

    // region Commands

    // endregion
}