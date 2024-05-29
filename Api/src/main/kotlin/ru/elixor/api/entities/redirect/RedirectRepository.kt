package ru.elixor.api.entities.redirect

import org.springframework.data.jpa.repository.JpaRepository
import ru.elixor.api.entities.link.LinkEntity
import java.util.*

interface RedirectRepository : JpaRepository<RedirectEntity, UUID> {
    fun deleteAllByLink(link: LinkEntity)
}