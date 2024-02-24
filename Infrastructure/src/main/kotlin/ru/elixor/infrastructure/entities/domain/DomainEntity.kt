package ru.elixor.infrastructure.entities.domain

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.util.*

@Entity
@Table(name = "DOMAINS")
class DomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "UID", unique = true)
    val uid: UUID? = null

    @Column(name = "NAME", nullable = false, unique = true, length = 16)
    var name: String? = null

    @CreationTimestamp
    @Column(name = "CREATE_DT", nullable = false)
    var createDt: Date? = null
}