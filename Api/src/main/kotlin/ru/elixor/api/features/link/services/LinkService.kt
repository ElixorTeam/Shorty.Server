package ru.elixor.api.features.link.services

import ru.elixor.api.features.link.dto.LinkOutputDto
import java.util.*

interface LinkService {
    fun getAll(userUid: UUID): List<LinkOutputDto>
}