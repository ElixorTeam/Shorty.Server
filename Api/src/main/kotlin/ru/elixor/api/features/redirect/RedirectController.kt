package ru.elixor.api.features.redirect

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.elixor.api.features.redirect.common.RedirectService
import ru.elixor.api.features.redirect.dto.LinkRedirectOutputDto
import ru.elixor.api.features.redirect.request.params.UrlPathParam

@RestController
@RequestMapping("/api/v1/redirects")
class RedirectController(private val redirectService: RedirectService) {

    @GetMapping("link")
    fun findLinkByUrl(@ModelAttribute urlDto: UrlPathParam):
            LinkRedirectOutputDto = redirectService.findLinkByUrl(urlDto)
}