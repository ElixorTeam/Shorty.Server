package ru.elixor.api.entities.folder

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface FolderRepository : JpaRepository<FolderEntity, UUID> {
    fun findAllByUserUid(userUid: UUID) : List<FolderEntity>
}