package ru.elixor.api.features.redirect.common

import ru.elixor.api.features.redirect.dto.LinkRedirectOutputDto
import ru.elixor.api.features.redirect.request.params.UrlPathParam

interface RedirectService {
    fun findLinkByUrl(urlDto: UrlPathParam): LinkRedirectOutputDto
}