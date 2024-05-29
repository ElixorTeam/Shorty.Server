package ru.elixor.api.features.user.features.subdomain

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.elixor.api.configurations.security.annotations.UserUid
import ru.elixor.api.features.user.features.subdomain.common.SubDomainService
import ru.elixor.api.features.user.features.subdomain.dto.SubDomainCreateDto
import ru.elixor.api.features.user.features.subdomain.dto.output.SubDomainGroupOutputDto
import ru.elixor.api.features.user.features.subdomain.dto.output.SubDomainOutputDto
import java.util.*

@RestController
@RequestMapping("/api/v1/user/subdomains")
class SubDomainController(private val subDomainService: SubDomainService) {
    // region Queries

    @GetMapping
    fun getAll(@UserUid userUid: UUID): SubDomainGroupOutputDto = subDomainService.getAll(userUid)

    // endregion

    // region CRUD

    @PostMapping
    fun create(@RequestBody @Validated dto: SubDomainCreateDto, @UserUid userUid: UUID):
            SubDomainOutputDto = subDomainService.create(dto, userUid)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID, @UserUid userUid: UUID) = subDomainService.delete(id, userUid)

    //endregion
}