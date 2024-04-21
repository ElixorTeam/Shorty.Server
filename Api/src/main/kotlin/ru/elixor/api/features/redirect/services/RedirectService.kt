package ru.elixor.api.features.redirect.services

import ru.elixor.api.features.redirect.dto.LinkRedirectOutputDto
import ru.elixor.api.features.redirect.request.params.UrlPathParam

interface RedirectService {
    fun findLinkByUrl(urlDto: UrlPathParam): LinkRedirectOutputDto
}