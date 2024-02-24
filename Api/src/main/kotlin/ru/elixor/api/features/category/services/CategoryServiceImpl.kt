package ru.elixor.api.features.category.services

import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Service
import ru.elixor.api.features.category.dto.CategoryCreateDto
import ru.elixor.api.features.category.dto.CategoryOutputDto
import ru.elixor.api.features.category.dto.CategoryUpdateDto
import ru.elixor.api.features.link.dto.LinkOutputDto
import java.util.*


@Service
class CategoryServiceImpl : CategoryService {
    override fun getAll(jwt: Jwt): List<CategoryOutputDto> {
        TODO("Not yet implemented")
    }

    override fun update(linkId: UUID, categoryUpdateDto: CategoryUpdateDto, jwt: Jwt): CategoryOutputDto {
        TODO("Not yet implemented")
    }

    override fun create(categoryCreateDto: CategoryCreateDto, jwt: Jwt): LinkOutputDto {
        TODO("Not yet implemented")
    }
}