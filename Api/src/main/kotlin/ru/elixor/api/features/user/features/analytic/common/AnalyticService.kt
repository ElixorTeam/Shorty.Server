package ru.elixor.api.features.user.features.analytic.common

import ru.elixor.api.enums.TimePeriod
import ru.elixor.api.features.user.features.analytic.dto.AnalyticOutputDto
import java.util.*

interface AnalyticService {
    // region Queries

    fun get(linkId: UUID, userUid: UUID, type: TimePeriod): AnalyticOutputDto

    // endregion

    // region CRUD

    fun delete(linkId: UUID, userUid: UUID)

    // endregion
}