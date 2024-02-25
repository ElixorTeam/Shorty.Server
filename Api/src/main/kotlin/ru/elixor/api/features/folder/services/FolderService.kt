package ru.elixor.api.features.folder.services

import org.springframework.security.oauth2.jwt.Jwt
import ru.elixor.api.features.folder.dto.FolderCreateDto
import ru.elixor.api.features.folder.dto.FolderOutputDto
import ru.elixor.api.features.folder.dto.FolderUpdateDto
import ru.elixor.api.features.link.dto.LinkOutputDto
import java.util.*

interface FolderService {
    fun getAll(jwt: Jwt): List<FolderOutputDto>
    fun update(linkId: UUID, folderUpdateDto: FolderUpdateDto, jwt: Jwt): FolderOutputDto
    fun create(folderCreateDto: FolderCreateDto, jwt: Jwt): LinkOutputDto
}