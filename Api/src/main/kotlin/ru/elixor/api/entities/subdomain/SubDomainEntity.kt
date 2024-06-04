package ru.elixor.api.entities.subdomain

import jakarta.persistence.*
import ru.elixor.api.entities.domain.DomainEntity
import ru.elixor.api.utils.DefaultTypesUtil
import java.util.*


@Entity
@Table(
    name = "SUBDOMAINS",
    indexes = [Index(name = "IX_SUBDOMAINS___USER", columnList = "USER_UID", unique = false)],
    uniqueConstraints = [UniqueConstraint(name = "UQ_SUBDOMAINS___USER__VALUE", columnNames = ["USER_UID", "VALUE"])]
)
class SubDomainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "UID", unique = true)
    val uid: UUID = DefaultTypesUtil.guid

    @Column(name = "USER_UID", nullable = false)
    var userUid: UUID = DefaultTypesUtil.guid

    @ManyToOne(optional = false)
    @JoinColumn(name = "DOMAIN_UID", foreignKey = ForeignKey(name = "FK_SUBDOMAINS___DOMAIN"), nullable = false)
    var domain: DomainEntity = DomainEntity()

    @Column(name = "VALUE", nullable = false, length = 16)
    var value: String = ""
}