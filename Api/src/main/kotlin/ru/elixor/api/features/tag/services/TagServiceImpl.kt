package ru.elixor.api.features.tag.services

import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elixor.api.entities.tag.TagRepository
import ru.elixor.api.features.tag.dto.*
import java.util.*


@Service
class TagServiceImpl(private val folderRepository: TagRepository) : TagService {
    override fun getAll(jwt: Jwt): TagOutputDtoWrapper {
        return folderRepository.findAllByUserUid(UUID.fromString(jwt.subject)).toWrapperDto()
    }

    @Transactional
    override fun update(linkId: UUID, tagUpdateDto: TagUpdateDto, jwt: Jwt): TagOutputDto {
        TODO("Not yet implemented")
    }
}