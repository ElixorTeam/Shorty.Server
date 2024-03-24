package ru.elixor.api.entities.link

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import ru.elixor.api.entities.domain.DomainEntity
import ru.elixor.api.entities.tag.TagEntity
import ru.elixor.api.utils.DefaultTypesUtil
import ru.elixor.api.utils.jpa.UrlConverter
import java.net.URL
import java.util.*


@Entity
@Table(
    name = "LINKS",
    uniqueConstraints = [UniqueConstraint(name = "UQ_LINKS_USER_TITLE", columnNames = ["USER_UID", "TITLE"])]
)
class LinkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "UID", unique = true)
    val uid: UUID = DefaultTypesUtil.guid

    @Column(name = "USER_UID", nullable = false)
    var userUid: UUID = DefaultTypesUtil.guid

    @Column(name = "TITLE", nullable = false, length = 64)
    var title: String = ""

    @ManyToOne(optional = false)
    @JoinColumn(name = "DOMAIN_UID", foreignKey = ForeignKey(name = "FK_LINKS_DOMAIN"), nullable = false)
    var domain: DomainEntity = DomainEntity()

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "LINKS_TAGS",
        joinColumns = [JoinColumn(name = "LINK_UID", foreignKey = ForeignKey(name = "FK_LINKS_TAGS_LINK"))],
        inverseJoinColumns = [JoinColumn(name = "TAG_UID", foreignKey = ForeignKey(name = "FK_LINKS_TAGS_TAG"))],
        uniqueConstraints = [UniqueConstraint(name = "UQ_LINKS_TAGS", columnNames = ["LINK_UID", "TAG_UID"])]
    )
    var tags: MutableSet<TagEntity> = HashSet()

    @Column(name = "SUBDOMAIN", unique = true, nullable = false, length = 12)
    var subdomain: String = ""

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