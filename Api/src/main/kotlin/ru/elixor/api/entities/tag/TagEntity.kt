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
    val userUid: UUID? = null

    @Column(name = "TITLE", length = 16)
    var title: String = ""
}
