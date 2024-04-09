package ru.elixor.api.entities.domain

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import ru.elixor.api.utils.DefaultTypesUtil
import java.util.*

@Entity
@Table(
    name = "DOMAINS",
    uniqueConstraints = [UniqueConstraint(name = "UQ_DOMAINS_VALUE", columnNames = ["VALUE"])]
)
class DomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "UID")
    val uid: UUID = DefaultTypesUtil.guid

    @Column(name = "VALUE", nullable = false, length = 16)
    var value: String = ""

    @CreationTimestamp
    @Column(name = "CREATE_DT", nullable = false)
    val createDt: Date = DefaultTypesUtil.date
}