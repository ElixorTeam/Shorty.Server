package ru.elixor.api.features.redirect.common

import org.springframework.http.ResponseEntity
import ru.elixor.api.features.redirect.dto.LinkRedirectOutputDto
import ru.elixor.api.features.redirect.dto.RedirectCreateDto
import ru.elixor.api.features.redirect.request.params.UrlPathParam

interface RedirectService {
    // region Queries

    fun findLinkByUrl(urlDto: UrlPathParam): LinkRedirectOutputDto

    // endregion

    // region CRUD

    fun create(dto: RedirectCreateDto): ResponseEntity<Void>

    // endregion
}