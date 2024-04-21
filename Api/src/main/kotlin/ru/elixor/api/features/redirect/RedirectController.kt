package ru.elixor.api.features.redirect

import org.springframework.web.bind.annotation.*
import ru.elixor.api.features.redirect.dto.LinkRedirectOutputDto
import ru.elixor.api.features.redirect.request.params.UrlPathParam
import ru.elixor.api.features.redirect.services.RedirectService

@RestController
@RequestMapping("/api/v1/redirects")
class RedirectController(private val redirectService: RedirectService) {

    @GetMapping("link")
    fun findLinkByUrl(@ModelAttribute urlDto: UrlPathParam):
            LinkRedirectOutputDto = redirectService.findLinkByUrl(urlDto)
}