package ru.elixor.api.features.domain

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.elixor.api.features.domain.dto.DomainCreateDto
import ru.elixor.api.features.domain.dto.DomainOutputDto
import ru.elixor.api.features.domain.services.DomainService

@RestController
@RequestMapping("/api/v1/domains")
class DomainController(private val domainService: DomainService) {

    // region Queries
    @GetMapping
    fun getAll(@AuthenticationPrincipal jwt: Jwt): List<DomainOutputDto> = domainService.getAll(jwt)

    // endregion

    // region Commands

    @PostMapping
    @PreAuthorize("hasAnyRole('admin')")
    fun create(@RequestBody @Validated dto: DomainCreateDto, @AuthenticationPrincipal jwt: Jwt):
            DomainOutputDto = domainService.create(dto, jwt)

    // endregion
}