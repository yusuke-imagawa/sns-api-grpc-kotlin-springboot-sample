package com.talking.data.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.Type
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "profile_images")
data class ProfileImage (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User = User(),

    @Column(name = "file_name")
    var fileName: String = "",

    @Column(name = "exists_original")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    var existsOriginal: Boolean = false,

    @Column(name = "exists_100")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    var exists100: Boolean = false,

    @Column(name = "exists_300")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    var exists300: Boolean = false,

    @Column(name = "exists_600")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    var exists600: Boolean = false,

    @Column(name = "status", nullable = false)
    @Convert(converter = ProfileImageStatusConverter::class)
    var status: ProfileImageStatus = ProfileImageStatus.ENABLE
) {
    @Column(name = "created", nullable = false)
    @CreationTimestamp
    lateinit var created: LocalDateTime

    @Column(name = "modified", nullable = false)
    @UpdateTimestamp
    lateinit var modified: LocalDateTime
}

enum class ProfileImageStatus(val value: String) {
    ENABLE("enable"), DELETED_BY_USER("deleted_by_user"), DELETED_BY_ADMIN("deleted_by_admin");
}

@Converter
class ProfileImageStatusConverter: AttributeConverter<ProfileImageStatus, String> {

    override fun convertToDatabaseColumn(attribute: ProfileImageStatus): String {
        return when (attribute) {
            ProfileImageStatus.ENABLE -> ProfileImageStatus.ENABLE.value
            ProfileImageStatus.DELETED_BY_USER -> ProfileImageStatus.DELETED_BY_USER.value
            ProfileImageStatus.DELETED_BY_ADMIN -> ProfileImageStatus.DELETED_BY_ADMIN.value
        }
    }

    override fun convertToEntityAttribute(dbData: String?): ProfileImageStatus? {
        return when (dbData) {
            ProfileImageStatus.ENABLE.value -> ProfileImageStatus.ENABLE
            ProfileImageStatus.DELETED_BY_USER.value -> ProfileImageStatus.DELETED_BY_USER
            ProfileImageStatus.DELETED_BY_ADMIN.value -> ProfileImageStatus.DELETED_BY_ADMIN
            else -> null
        }
    }
}