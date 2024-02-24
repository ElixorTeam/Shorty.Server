package ru.elixor.infrastructure.entities.domain

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface DomainRepository : JpaRepository<DomainEntity, UUID>