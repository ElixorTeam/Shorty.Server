package ru.elixor.api.entities.link

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import ru.elixor.api.entities.domain.DomainEntity
import ru.elixor.api.entities.folder.FolderEntity
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
    val uid: UUID? = null

    @Column(name = "USER_UID", nullable = false)
    val userUid: UUID? = null

    @Column(name = "TITLE", nullable = false, length = 64)
    val title: String = ""

    @ManyToOne(optional = false)
    @JoinColumn(name = "DOMAIN_UID", nullable = false)
    val domain: DomainEntity? = null

    @ManyToOne(optional = false)
    @JoinColumn(name = "FOLDER_UID", nullable = true)
    val folder: FolderEntity? = null

    @Column(name = "SUBDOMAIN", unique = true, nullable = false, length = 12)
    var subdomain: String = ""

    @Convert(converter = UrlConverter::class)
    @Column(name = "URL", nullable = false, length = 250)
    var url: URL? = null

    @Column(name = "PASSWORD", nullable = true, length = 24)
    val password: String? = null

    @CreationTimestamp
    @Column(name = "CREATE_DT", nullable = false)
    val createDt: Date? = null;

    @UpdateTimestamp
    @Column(name = "UPDATE_DT", nullable = false)
    val updateDt: Date? = null;
}