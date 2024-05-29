package ru.elixor.api.features.user.features.analytic.common

import java.util.*

interface AnalyticService {
    fun delete(linkId: UUID, userUid: UUID)
}