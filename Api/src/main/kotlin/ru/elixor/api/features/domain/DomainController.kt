package ru.elixor.api.features.domain

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.elixor.api.configurations.security.auth.RoleConstants;
import ru.elixor.api.features.domain.common.DomainService
import ru.elixor.api.features.domain.dto.DomainCreateDto
import ru.elixor.api.features.domain.dto.DomainOutputDto
import ru.elixor.api.features.domain.dto.DomainsOutputDtoWrapper

@RestController
@RequestMapping("/api/v1/domains")
class DomainController(private val domainService: DomainService) {
    // region Queries

    @GetMapping
    fun getAll(): DomainsOutputDtoWrapper = domainService.getAll()

    // endregion

    // region CRUD

    @PostMapping
    @PreAuthorize("hasAnyRole('${RoleConstants.ADMIN}')")
    fun create(@RequestBody @Validated dto: DomainCreateDto): DomainOutputDto = domainService.create(dto)

    // endregion
}