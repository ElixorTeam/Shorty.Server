package ru.elixor.api.entities.tag

import jakarta.persistence.*
import ru.elixor.api.utils.DefaultTypesUtil
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
    val uid: UUID = DefaultTypesUtil.guid

    @Column(name = "USER_UID")
    var userUid: UUID = DefaultTypesUtil.guid

    @Column(name = "VALUE", nullable = false, columnDefinition = "NVARCHAR(16)")
    var value: String = ""

    // region Equals

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TagEntity) return false
        return uid == other.uid && userUid == other.userUid && value == other.value
    }

    override fun hashCode(): Int {
        var result = uid.hashCode()
        result = 31 * result + userUid.hashCode()
        result = 31 * result + value.hashCode()
        return result
    }

    // endregion
}