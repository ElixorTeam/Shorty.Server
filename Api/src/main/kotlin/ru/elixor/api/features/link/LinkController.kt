import org.springframework.ui.Model;
import org.springframework.stereotype.Controller
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.elixor.api.configurations.security.UserUid
import ru.elixor.api.features.link.dto.LinkCreateDto
import ru.elixor.api.features.link.dto.LinkOutputDto
import ru.elixor.api.features.link.dto.LinkUpdateDto
import ru.elixor.api.features.link.services.LinkService
import java.util.*

@Controller
@RequestMapping("/links")
class LinkController(private val linkService: LinkService) {
    // region Queries

    @GetMapping
    fun getAll(@UserUid userUid: UUID, model: Model): String {
        model.addAttribute("links", linkService.getAll(userUid));
        return "links"
    }

    // endregion

    // region Commands

    @PostMapping
    fun create(@RequestBody @Validated dto: LinkCreateDto, @UserUid userUid: UUID):
            LinkOutputDto = linkService.create(dto, userUid)

    @PutMapping("/{id}")
    fun update(@PathVariable id: UUID, @RequestBody @Validated dto: LinkUpdateDto, @UserUid userUid: UUID):
            LinkOutputDto = linkService.update(id, dto, userUid)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID, @UserUid userUid: UUID) = linkService.delete(id, userUid)

    @GetMapping("/{id}")
    fun get(@PathVariable id: UUID, @UserUid userUid: UUID) = linkService.getLinkById(id, userUid)

    // endregion
}
