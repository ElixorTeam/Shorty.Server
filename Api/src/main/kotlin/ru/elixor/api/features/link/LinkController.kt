package ru.elixor.api.features.link

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import ru.elixor.api.configurations.security.UserUid
import ru.elixor.api.features.link.services.LinkService
import java.util.*

@Controller
@RequestMapping("/links")
class LinkController(private val linkService: LinkService) {
    @GetMapping
    fun getAll(@UserUid userUid: UUID, model: Model): String {
        model.addAttribute("links", linkService.getAll(userUid));
        return "links"
    }
}
