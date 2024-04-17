package ru.elixor.api.features.user.features.sub.domain

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.elixor.api.configurations.security.UserUid
import ru.elixor.api.features.user.features.sub.domain.dto.SubDomainCreateDto
import ru.elixor.api.features.user.features.sub.domain.dto.SubDomainOutputDto
import ru.elixor.api.features.user.features.sub.domain.dto.SubDomainOutputDtoWrapper
import ru.elixor.api.features.user.features.sub.domain.services.SubDomainService
import java.util.*

@RestController
@RequestMapping("/api/v1/user/subdomains")
class SubDomainController(private val subDomainService: SubDomainService) {
    // region Queries

    @GetMapping(params = ["domainUid"])
    fun getAllDomainUid(@RequestParam("domainUid", required = true) domainUid: UUID, @UserUid userUid: UUID) :
            SubDomainOutputDtoWrapper = subDomainService.getAllByDomainUid(userUid, domainUid)

    // endregion

    // region Commands

    @PostMapping
    fun create(@RequestBody @Validated dto: SubDomainCreateDto, @UserUid userUid: UUID):
            SubDomainOutputDto = subDomainService.create(dto, userUid)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID, @UserUid userUid: UUID) = subDomainService.delete(id, userUid)

    //endregion
}