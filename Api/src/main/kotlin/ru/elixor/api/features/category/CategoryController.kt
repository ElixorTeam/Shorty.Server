package ru.elixor.api.features.category

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.elixor.api.features.category.dto.CategoryOutputDto
import ru.elixor.api.features.category.services.CategoryService

@RestController
@RequestMapping("/api/v1/categories")
class CategoryController(private val categoryService: CategoryService)
{
    // region Queries
    @GetMapping
    fun getAll(@AuthenticationPrincipal jwt: Jwt): List<CategoryOutputDto> = categoryService.getAll(jwt)

    // endregion

    // region Commands

    // endregion
}