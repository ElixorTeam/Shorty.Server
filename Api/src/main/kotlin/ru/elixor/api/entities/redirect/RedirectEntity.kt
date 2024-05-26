package ru.elixor.api.entities.redirect

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import ru.elixor.api.entities.domain.DomainEntity
import ru.elixor.api.entities.link.LinkEntity
import ru.elixor.api.entities.subdomain.SubDomainEntity
import ru.elixor.api.enums.DeviceTypes
import ru.elixor.api.utils.DefaultTypesUtil
import java.net.InetAddress
import java.util.*

@Entity
@Table(name = "REDIRECTS")
class RedirectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "UID", unique = true)
    val uid: UUID = DefaultTypesUtil.guid

    @ManyToOne(optional = false)
    @JoinColumn(name = "LINK_UID", foreignKey = ForeignKey(name = "FK_REDIRECTS_LINK"), nullable = false)
    var link: LinkEntity = LinkEntity()

    @Column(name = "CLIENT_KEY", nullable = false)
    var clientKey: UUID = DefaultTypesUtil.guid

    @Enumerated(EnumType.STRING)
    @Column(name = "DEVICE", nullable = false, length = 16)
    var device: DeviceTypes = DeviceTypes.Desktop

    @Column(name = "OS", nullable = false, length = 16)
    var os: String = ""

    @Column(name = "IP_V4", nullable = false, length = 15)
    var ip: InetAddress = InetAddress.getByName("0.0.0.0")

    @CreationTimestamp
    @Column(name = "CREATE_DT", nullable = false)
    val createDt: Date = DefaultTypesUtil.date
}