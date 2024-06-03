package ru.elixor.api.entities.url

import jakarta.persistence.*
import ru.elixor.api.entities.link.LinkEntity
import ru.elixor.api.utils.DefaultTypesUtil
import ru.elixor.api.utils.jpa.UrlConverter
import java.net.URL
import java.util.*


@Entity
@Table(
    name = "URLS",
    uniqueConstraints = [UniqueConstraint(name = "UQ_URLS_URL_LINK_UID", columnNames = ["URL", "LINK_UID"])]
)
class UrlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "UID")
    val uid: UUID = DefaultTypesUtil.guid

    @Convert(converter = UrlConverter::class)
    @Column(name = "URL", nullable = false, length = 250)
    var url: URL = DefaultTypesUtil.url

    @ManyToOne(optional = false)
    @JoinColumn(name = "LINK_UID", nullable = false, foreignKey = ForeignKey(name = "FK_URLS_LINK"))
    var link: LinkEntity = LinkEntity()

    // region Equals

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UrlEntity) return false
        return uid == other.uid && link.uid == other.link.uid && Objects.equals(url, other.url);
    }

    override fun hashCode(): Int {
        var result = uid.hashCode()
        result = 31 * result + link.uid.hashCode()
        result = 31 * result +  Objects.hash(url)
        return result
    }

    // endregion
}