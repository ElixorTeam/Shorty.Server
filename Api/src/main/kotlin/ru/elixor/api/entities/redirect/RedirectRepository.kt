package ru.elixor.api.entities.redirect

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import ru.elixor.api.entities.link.LinkEntity
import java.time.LocalDateTime
import java.util.*

interface RedirectRepository : JpaRepository<RedirectEntity, UUID> {
    fun deleteAllByLink(link: LinkEntity)

    @Query("SELECT r FROM RedirectEntity r WHERE r.link = :link AND r.createDt >= :startDate")
    fun findAllByLinkAndCreateDtAfter(link: LinkEntity, startDate: LocalDateTime): List<RedirectEntity>
}