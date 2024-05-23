package ru.elixor.api.entities.redirect

import jakarta.persistence.*
import ru.elixor.api.entities.domain.DomainEntity
import ru.elixor.api.entities.subdomain.SubDomainEntity
import ru.elixor.api.utils.DefaultTypesUtil
import java.util.*

//@Entity
//@Table(name = "REDIRECTS")
class RedirectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "UID", unique = true)
    val uid: UUID = DefaultTypesUtil.guid

    @Column(name = "CLIENT_KEY", nullable = false, length = 16)
    var clientKey: UUID = DefaultTypesUtil.guid

    @Column(name = "DEVICE", nullable = false, length = 16)
    var device: String = ""

    @Column(name = "OS", nullable = false, length = 16)
    var os: String = ""

    @Column(name = "PATH", nullable = false, length = 16)
    var path: String = ""

//    @Column(name = "IP_V4", nullable = false, length = 16)
//    var ip: InetAddress = ""

    @ManyToOne(optional = false)
    @JoinColumn(name = "DOMAIN_UID", foreignKey = ForeignKey(name = "FK_REDIRECTS_DOMAIN"), nullable = false)
    var domain: DomainEntity = DomainEntity()

    @ManyToOne(optional = true)
    @JoinColumn(name = "SUBDOMAIN_UID", foreignKey = ForeignKey(name = "FK_REDIRECTS_SUBDOMAIN"), nullable = true)
    var subdomain: SubDomainEntity? = null
}