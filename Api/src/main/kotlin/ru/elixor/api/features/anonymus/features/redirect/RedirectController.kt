package ru.elixor.api.features.anonymus.features.redirect

import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.elixor.api.features.anonymus.features.redirect.common.RedirectService
import ru.elixor.api.features.anonymus.features.redirect.dto.LinkRedirectOutputDto
import ru.elixor.api.features.anonymus.features.redirect.dto.RedirectCreateDto
import ru.elixor.api.features.anonymus.features.redirect.request.params.UrlPathParam

@RestController
@RequestMapping("/api/v1/redirects")
class RedirectController(private val redirectService: RedirectService) {
    // region Queries

    @GetMapping("link")
    fun findLinkByUrl(@ModelAttribute urlDto: UrlPathParam):
            LinkRedirectOutputDto = redirectService.findLinkByUrl(urlDto)

    // endregion

    // region CRUD

    @PostMapping
    fun create(@RequestBody @Validated dto: RedirectCreateDto): ResponseEntity<Void> = redirectService.create(dto)

    // endregion
}