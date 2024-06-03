package ru.elixor.api.entities.url

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UrlRepository : JpaRepository<UrlEntity, UUID>