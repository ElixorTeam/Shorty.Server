package ru.elixor.api.features.link

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping


@Controller
class IndexController {
    @GetMapping(path = ["/"])
    fun index(): String {
        return "external"
    }
}