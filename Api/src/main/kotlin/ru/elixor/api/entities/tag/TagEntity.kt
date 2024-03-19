package ru.elixor.api.entities.tag

import jakarta.persistence.*
import java.util.*

@Entity
@Table(
    name = "TAGS",
    uniqueConstraints = [UniqueConstraint(name = "UQ_TAGS_USER_NAME", columnNames = ["USER_UID", "TITLE"])]
)
class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "UID", unique = true)
    val uid: UUID? = null

    @Column(name = "USER_UID")
    var userUid: UUID? = null

    @Column(name = "TITLE", length = 16)
    var title: String = ""

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TagEntity) return false
        return uid == other.uid && userUid == other.userUid && title == other.title
    }

    override fun hashCode(): Int {
        var result = uid?.hashCode() ?: 0
        result = 31 * result + (userUid?.hashCode() ?: 0)
        result = 31 * result + title.hashCode()
        return result
    }
}
