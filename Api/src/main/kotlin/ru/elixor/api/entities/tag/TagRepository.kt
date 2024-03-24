package ru.elixor.api.entities.tag

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import java.util.*

interface TagRepository : JpaRepository<TagEntity, UUID> {
    fun findAllByUserUid(userUid: UUID) : List<TagEntity>
    fun findFirstByUserUidAndTitle(userUid: UUID, title: String) : TagEntity?
    fun findAllByUserUidAndTitleIn(userUid: UUID, title: MutableCollection<String>) : List<TagEntity>

    @Modifying
    @Query("DELETE FROM TagEntity t WHERE t NOT IN " +
            "(SELECT DISTINCT tag FROM LinkEntity link JOIN link.tags tag WHERE link.userUid = :userUid)")
    fun deleteUnused(userUid: UUID)
}