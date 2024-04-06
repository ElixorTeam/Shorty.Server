package ru.elixor.api.features.user.features.sub.domain

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.elixor.api.configurations.security.UserUid
import ru.elixor.api.features.user.features.sub.domain.dto.SubDomainOutputDtoWrapper
import ru.elixor.api.features.user.features.sub.domain.services.SubDomainService
import java.util.*

@RestController
@RequestMapping("/api/v1/user/subdomains")
class SubDomainController(private val subDomainService: SubDomainService) {
    // region Queries

    @GetMapping("/", params = ["domainUid"])
    fun getAll(@RequestParam("domainUid", required = true) domainUid: UUID, @UserUid userUid: UUID) :
            SubDomainOutputDtoWrapper = subDomainService.getAllByDomainUid(userUid, domainUid)

    // endregion
}