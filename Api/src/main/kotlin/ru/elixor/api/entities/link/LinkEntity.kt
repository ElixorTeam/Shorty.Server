package ru.elixor.api.entities.link

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import ru.elixor.api.entities.domain.DomainEntity
import ru.elixor.api.entities.sub.domain.SubDomainEntity
import ru.elixor.api.entities.tag.TagEntity
import ru.elixor.api.utils.DefaultTypesUtil
import ru.elixor.api.utils.jpa.UrlConverter
import java.net.URL
import java.util.*


@Entity
@Table(name = "LINKS")
class LinkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "UID", unique = true)
    val uid: UUID = DefaultTypesUtil.guid

    @Column(name = "USER_UID", nullable = false)
    var userUid: UUID = DefaultTypesUtil.guid

    @Column(name = "TITLE", nullable = false, columnDefinition = "NVARCHAR(64)")
    var title: String = ""

    @Column(name = "PATH", nullable = false, length = 16)
    var path: String = ""

    @ManyToOne(optional = false)
    @JoinColumn(name = "DOMAIN_UID", foreignKey = ForeignKey(name = "FK_LINKS_DOMAIN"), nullable = false)
    var domain: DomainEntity = DomainEntity()

    @ManyToOne(optional = true)
    @JoinColumn(name = "SUBDOMAIN_UID", foreignKey = ForeignKey(name = "FK_LINKS_SUBDOMAIN"), nullable = true)
    var subdomain: SubDomainEntity? = null

    @Column(name = "IS_ENABLE", nullable = true)
    var isEnable: Boolean = true

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "LINKS_TAGS",
        joinColumns = [JoinColumn(name = "LINK_UID", foreignKey = ForeignKey(name = "FK_LINKS_TAGS_LINK"))],
        inverseJoinColumns = [JoinColumn(name = "TAG_UID", foreignKey = ForeignKey(name = "FK_LINKS_TAGS_TAG"))],
        uniqueConstraints = [UniqueConstraint(name = "UQ_LINKS_TAGS", columnNames = ["LINK_UID", "TAG_UID"])]
    )
    var tags: MutableSet<TagEntity> = HashSet()

    @Convert(converter = UrlConverter::class)
    @Column(name = "URL", nullable = false, length = 250)
    var url: URL = DefaultTypesUtil.url

    @Column(name = "PASSWORD", nullable = true, length = 24)
    var password: String? = null

    @CreationTimestamp
    @Column(name = "CREATE_DT", nullable = false)
    val createDt: Date = DefaultTypesUtil.date

    @UpdateTimestamp
    @Column(name = "UPDATE_DT", nullable = false)
    val updateDt: Date = DefaultTypesUtil.date
}