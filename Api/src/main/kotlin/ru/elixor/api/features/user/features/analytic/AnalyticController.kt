package ru.elixor.api.features.user.features.analytic

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import ru.elixor.api.configurations.security.annotations.UserUid
import ru.elixor.api.features.user.features.analytic.common.AnalyticService
import java.util.*

@RequestMapping("/api/v1/user/links/analytics")
class AnalyticController(private val analyticService: AnalyticService) {

    // region CRUD

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID, @UserUid userUid: UUID) = analyticService.delete(id, userUid)

    // endregion
}