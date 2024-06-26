package ru.elixor.api.features.user.features.analytic.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import ru.elixor.api.entities.link.LinkEntity
import ru.elixor.api.entities.link.LinkRepository
import ru.elixor.api.exceptions.errors.NotFoundException
import java.util.*


@Service
class AnalyticServiceHelper(private val linkRepo: LinkRepository) {

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    fun getLinkByIdAndUser(linkId: UUID, userUid: UUID): LinkEntity =
        linkRepo.findByUidAndUserUid(linkId, userUid).orElseThrow {
            NotFoundException()
        }
}