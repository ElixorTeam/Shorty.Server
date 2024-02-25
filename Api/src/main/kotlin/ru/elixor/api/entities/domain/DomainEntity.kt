package ru.elixor.api.entities.domain

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.util.*

@Entity
@Table(
    name = "DOMAINS",
    uniqueConstraints = [UniqueConstraint(name = "UQ_DOMAINS_VALUE", columnNames = ["VALUE"])]
)
class DomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "UID", unique = true)
    val uid: UUID? = null

    @Column(name = "VALUE", nullable = false, length = 16)
    var value: String? = null

    @CreationTimestamp
    @Column(name = "CREATE_DT", nullable = false)
    var createDt: Date? = null
}