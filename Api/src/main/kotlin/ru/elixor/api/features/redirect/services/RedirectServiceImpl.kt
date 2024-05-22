package ru.elixor.api.features.redirect.services

import org.springframework.stereotype.Service
import ru.elixor.api.entities.link.LinkRepository
import ru.elixor.api.exceptions.errors.NotFoundException
import ru.elixor.api.features.redirect.common.RedirectService
import ru.elixor.api.features.redirect.dto.LinkRedirectOutputDto
import ru.elixor.api.features.redirect.dto.toDto
import ru.elixor.api.features.redirect.request.params.UrlPathParam

@Service
class RedirectServiceImpl(private val linkRepo: LinkRepository) : RedirectService {

    override fun findLinkByUrl(urlDto: UrlPathParam): LinkRedirectOutputDto {
        return linkRepo.findByDomainValueAndSubdomainValueAndPath(urlDto.domain, urlDto.subdomain, urlDto.path)
            .orElseThrow { NotFoundException() }.toDto()
    }

}