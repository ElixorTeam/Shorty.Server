package ru.elixor.api.features.folder.services

import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elixor.api.entities.folder.FolderRepository
import ru.elixor.api.features.folder.dto.FolderCreateDto
import ru.elixor.api.features.folder.dto.FolderOutputDto
import ru.elixor.api.features.folder.dto.FolderUpdateDto
import ru.elixor.api.features.link.dto.LinkOutputDto
import java.util.*


@Service
class FolderServiceImpl(private val folderRepository: FolderRepository) : FolderService {
    override fun getAll(jwt: Jwt): List<FolderOutputDto> {
        folderRepository.findAllByUserUid(UUID.fromString(jwt.subject))
        return listOf()
    }

    @Transactional
    override fun update(linkId: UUID, folderUpdateDto: FolderUpdateDto, jwt: Jwt): FolderOutputDto {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun create(folderCreateDto: FolderCreateDto, jwt: Jwt): LinkOutputDto {
        TODO("Not yet implemented")
    }
}