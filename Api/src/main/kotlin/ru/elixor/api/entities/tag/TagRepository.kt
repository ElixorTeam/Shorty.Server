package ru.elixor.api.entities.tag

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TagRepository : JpaRepository<TagEntity, UUID> {
    fun findAllByUserUid(userUid: UUID) : List<TagEntity>
    fun findAllByUserUidAndTitle(userUid: UUID, title: String) : TagEntity?
}