package ru.elixor.api.features.user.features.analytic

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.elixor.api.configurations.security.annotations.UserUid
import ru.elixor.api.features.user.features.analytic.common.AnalyticService
import ru.elixor.api.features.user.features.analytic.dto.AnalyticOutputDto
import java.util.*

@RestController
@RequestMapping("/api/v1/user/links/analytics")
class AnalyticController(private val analyticService: AnalyticService) {

    // region Queries

    @GetMapping("/{id}")
    fun getAll(@PathVariable id: UUID, @UserUid userUid: UUID): AnalyticOutputDto = analyticService.get(id, userUid)

    // endregion

    // region CRUD

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID, @UserUid userUid: UUID) = analyticService.delete(id, userUid)

    // endregion
}