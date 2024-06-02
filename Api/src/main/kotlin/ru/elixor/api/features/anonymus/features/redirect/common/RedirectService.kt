package ru.elixor.api.features.anonymus.features.redirect.common

import ru.elixor.api.features.anonymus.features.redirect.dto.LinkRedirectOutputDto
import ru.elixor.api.features.anonymus.features.redirect.dto.RedirectCreateDto
import ru.elixor.api.features.anonymus.features.redirect.request.params.UrlPathParam

interface RedirectService {
    // region Queries

    fun findLinkByUrl(urlDto: UrlPathParam): LinkRedirectOutputDto

    // endregion

    // region CRUD

    fun create(dto: RedirectCreateDto)

    // endregion
}