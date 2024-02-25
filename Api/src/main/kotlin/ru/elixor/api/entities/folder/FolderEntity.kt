package ru.elixor.api.entities.folder

import jakarta.persistence.*
import java.util.*

@Entity
@Table(
    name = "FOLDERS",
    uniqueConstraints = [UniqueConstraint(name = "UQ_FOLDERS_USER_NAME", columnNames = ["USER_UID", "NAME"])]
)
class FolderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "UID", unique = true)
    val uid: UUID? = null

    @Column(name = "USER_UID", unique = false)
    val userUid: UUID? = null

    @Column(name = "TITLE", nullable = false, length = 16)
    var title: String? = null
}
