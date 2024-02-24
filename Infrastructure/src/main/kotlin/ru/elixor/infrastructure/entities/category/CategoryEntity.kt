package ru.elixor.infrastructure.entities.category

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.util.*

@Entity
@Table(
    name = "CATEGORIES",
    uniqueConstraints = [UniqueConstraint(name = "UQ_CATEGORIES_USER_NAME", columnNames = ["USER_UID", "NAME"])]
)
class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "UID", unique = true)
    val uid: UUID? = null

    @Column(name = "USER_UID", unique = false)
    val userUid: UUID? = null

    @Column(name = "NAME", nullable = false, length = 16)
    var name: String? = null

    @CreationTimestamp
    @Column(name = "CREATE_DT", nullable = false)
    var createDt: Date? = null
}
