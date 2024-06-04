package ru.elixor.api.entities.link

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import ru.elixor.api.entities.domain.DomainEntity
import ru.elixor.api.entities.subdomain.SubDomainEntity
import ru.elixor.api.entities.tag.TagEntity
import ru.elixor.api.entities.url.UrlEntity
import ru.elixor.api.utils.DefaultTypesUtil
import java.util.*


@Entity
@Table(
    name = "LINKS",
    indexes = [Index(name = "IX_LINKS___USER", columnList = "USER_UID", unique = false)],
    uniqueConstraints = [UniqueConstraint(name = "UQ_LINKS___PATH__DOMAIN__SUBDOMAIN", columnNames = ["PATH", "DOMAIN_UID", "SUBDOMAIN_UID"])]
)
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
    @JoinColumn(name = "DOMAIN_UID", foreignKey = ForeignKey(name = "FK_LINKS___DOMAIN"), nullable = false)
    var domain: DomainEntity = DomainEntity()

    @ManyToOne(optional = true)
    @JoinColumn(name = "SUBDOMAIN_UID", foreignKey = ForeignKey(name = "FK_LINKS___SUBDOMAIN"), nullable = true)
    var subdomain: SubDomainEntity? = null

    @Column(name = "IS_ENABLE", nullable = true)
    var isEnable: Boolean = true

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "LINKS_TAGS",
        joinColumns = [JoinColumn(name = "LINK_UID", foreignKey = ForeignKey(name = "FK_LINKS_TAGS___LINK"))],
        inverseJoinColumns = [JoinColumn(name = "TAG_UID", foreignKey = ForeignKey(name = "FK_LINKS_TAGS___TAG"))],
        uniqueConstraints = [UniqueConstraint(name = "UQ_LINKS_TAGS___TAG__LINK", columnNames = ["LINK_UID", "TAG_UID"])]
    )
    var tags: MutableSet<TagEntity> = HashSet()

    @Column(name = "PASSWORD", nullable = true, length = 16)
    var password: String? = null

    @CreationTimestamp
    @Column(name = "CREATE_DT", nullable = false)
    val createDt: Date = DefaultTypesUtil.date

    @UpdateTimestamp
    @Column(name = "UPDATE_DT", nullable = false)
    val updateDt: Date = DefaultTypesUtil.date

    @OneToMany(mappedBy = "link", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    var urls: MutableSet<UrlEntity> =  HashSet()
}