package ru.elixor.api.entities.redirect

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RedirectRepository : JpaRepository<RedirectEntity, UUID>