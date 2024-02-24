package ru.elixor.api.features.category.services

import org.springframework.security.oauth2.jwt.Jwt
import ru.elixor.api.features.category.dto.CategoryCreateDto
import ru.elixor.api.features.category.dto.CategoryOutputDto
import ru.elixor.api.features.category.dto.CategoryUpdateDto
import ru.elixor.api.features.link.dto.LinkOutputDto
import java.util.*

interface CategoryService {
    fun getAll(jwt: Jwt): List<CategoryOutputDto>
    fun update(linkId: UUID, categoryUpdateDto: CategoryUpdateDto, jwt: Jwt): CategoryOutputDto
    fun create(categoryCreateDto: CategoryCreateDto, jwt: Jwt): LinkOutputDto
}