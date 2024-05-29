package ru.elixor.api.features.user.features.analytic.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elixor.api.entities.link.LinkRepository
import ru.elixor.api.entities.redirect.RedirectRepository
import ru.elixor.api.features.user.features.analytic.common.AnalyticService
import java.util.*

@Service
class AnalyticServiceImpl(
    private val redirectRepo: RedirectRepository,
    private val analyticHelper: AnalyticServiceHelper,
    private val linkRepo: LinkRepository
) : AnalyticService {

    @Transactional
    override fun delete(linkId: UUID, userUid: UUID) {
        val link = analyticHelper.getLinkByIdAndUser(linkId, userUid)
        redirectRepo.deleteAllByLink(link)
    }

}