package ru.elixor.api.features.redirect.services

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elixor.api.entities.link.LinkEntity
import ru.elixor.api.entities.link.LinkRepository
import ru.elixor.api.entities.redirect.RedirectRepository
import ru.elixor.api.exceptions.errors.NotFoundException
import ru.elixor.api.features.redirect.common.RedirectService
import ru.elixor.api.features.redirect.dto.LinkRedirectOutputDto
import ru.elixor.api.features.redirect.dto.RedirectCreateDto
import ru.elixor.api.features.redirect.dto.toDto
import ru.elixor.api.features.redirect.dto.toEntity
import ru.elixor.api.features.redirect.request.params.UrlPathParam

@Service
@Transactional
class RedirectServiceImpl(
    private val linkRepo: LinkRepository,
    private val redirectRepo: RedirectRepository
) : RedirectService {
    // region Queries

    @Transactional(readOnly = true)
    override fun findLinkByUrl(urlDto: UrlPathParam): LinkRedirectOutputDto {
        val link: LinkEntity =
            linkRepo.findByDomainValueAndSubdomainValueAndPath(urlDto.domain, urlDto.subdomain, urlDto.path)
                .orElseThrow { NotFoundException() }

        if (!link.isEnable) {
            throw NotFoundException();
        }

        return link.toDto()
    }

    // endregion

    // region CRUD

    override fun create(dto: RedirectCreateDto): ResponseEntity<Void> {
        val link: LinkEntity =
            linkRepo.findByDomainValueAndSubdomainValueAndPath(dto.domain, dto.subdomain, dto.path).orElseThrow {
                NotFoundException()
            };

        val redirect = dto.toEntity()
        redirect.link = link

        redirectRepo.save(redirect)
        return ResponseEntity.ok().build()
    }

    // endregion
}