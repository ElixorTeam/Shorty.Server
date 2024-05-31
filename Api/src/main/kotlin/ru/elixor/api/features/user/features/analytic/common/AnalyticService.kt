package ru.elixor.api.features.user.features.analytic.common

import ru.elixor.api.enums.PeriodType
import ru.elixor.api.features.user.features.analytic.dto.AnalyticOutputDto
import java.util.*

interface AnalyticService {
    fun get(linkId: UUID, userUid: UUID, type: PeriodType): AnalyticOutputDto
    fun delete(linkId: UUID, userUid: UUID)
}