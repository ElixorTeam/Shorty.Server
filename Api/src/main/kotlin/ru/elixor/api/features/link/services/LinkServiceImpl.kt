package ru.elixor.api.features.link.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elixor.api.entities.domain.DomainEntity
import ru.elixor.api.entities.domain.DomainRepository
import ru.elixor.api.entities.link.LinkEntity
import ru.elixor.api.entities.link.LinkRepository
import ru.elixor.api.exceptions.errors.NotFoundByUidException
import ru.elixor.api.features.link.dto.*
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Service
class LinkServiceImpl(private val linkRepository: LinkRepository) : LinkService {
    override fun getAll(userUid: UUID):
            List<LinkOutputDto> = linkRepository.findAllByUserUid(userUid).map { it.toDto() }
}
