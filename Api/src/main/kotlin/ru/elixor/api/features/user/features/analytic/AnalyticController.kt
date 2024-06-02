package ru.elixor.api.features.user.features.analytic

import org.springframework.web.bind.annotation.*
import ru.elixor.api.configurations.security.annotations.UserUid
import ru.elixor.api.enums.TimePeriod
import ru.elixor.api.features.user.features.analytic.common.AnalyticService
import ru.elixor.api.features.user.features.analytic.dto.AnalyticOutputDto
import java.util.*

@RestController
@RequestMapping("/api/v1/user/links/")
class AnalyticController(private val analyticService: AnalyticService) {

    // region Queries

    @GetMapping("/{id}/analytics")
    fun getAll(@PathVariable id: UUID, @UserUid userUid: UUID, @RequestParam("period") period: TimePeriod):
            AnalyticOutputDto = analyticService.get(id, userUid, period)

    // endregion

    // region CRUD

    @DeleteMapping("/{id}/analytics")
    fun delete(@PathVariable id: UUID, @UserUid userUid: UUID) = analyticService.delete(id, userUid)

    // endregion
}