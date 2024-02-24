package ru.elixor.infrastructure.entities.category

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CategoryRepository : JpaRepository<CategoryEntity, UUID> {
    fun findAllByUserUid(userUid: UUID) : List<CategoryEntity>
}