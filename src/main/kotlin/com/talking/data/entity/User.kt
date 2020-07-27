package com.talking.data.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.Type
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    var name: String? = null,

    @Column(name = "gender_type", nullable = false)
    @Convert(converter = GenderTypeConverter::class)
    var genderType: GenderType = GenderType.MALE,

    var age: Int = 0,

    @Lob
    var pr: String? = null,

    @Column(name = "status", nullable = false)
    @Convert(converter = StatusConverter::class)
    var status: UserStatus = UserStatus.ENABLE,

    @Column(name = "user_type", nullable = false)
    @Convert(converter = UserTypeConverter::class)
    var userType: UserType = UserType.USER,

    @Column(name = "api_token")
    var apiToken: String = "",

    @Column(name = "phone_number")
    var phoneNumber: String? = null,

    @Column(name = "is_enabled_notify")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    var isEnabledNotify: Boolean = false
) {
    @Column(name = "last_online_datetime", nullable = false)
    @CreationTimestamp
    lateinit var lastOnlineDatetime: LocalDateTime

    @Column(name = "created", nullable = false)
    @CreationTimestamp
    lateinit var created: LocalDateTime

    @Column(name = "modified", nullable = false)
    @UpdateTimestamp
    lateinit var modified: LocalDateTime
}

enum class GenderType(val value: String) {
    MALE("male"), FEMALE("female");
}

// DBとオブジェクト表現の変換用クラス
@Converter
class GenderTypeConverter: AttributeConverter<GenderType, String> {

    override fun convertToDatabaseColumn(attribute: GenderType): String {
        return when (attribute) {
            GenderType.MALE -> GenderType.MALE.value
            GenderType.FEMALE -> GenderType.FEMALE.value
        }
    }

    override fun convertToEntityAttribute(dbData: String?): GenderType? {
        return when (dbData) {
            GenderType.MALE.value -> GenderType.MALE
            GenderType.FEMALE.value -> GenderType.FEMALE
            else -> null
        }
    }
}

enum class UserStatus(val value: String) {
    ENABLE("enable"), LEAVE("leave"), FORCE_LEAVE("force_leave");
}

// DBとオブジェクト表現の変換用クラス
@Converter
class StatusConverter: AttributeConverter<UserStatus, String> {

    override fun convertToDatabaseColumn(attribute: UserStatus): String {
        return when (attribute) {
            UserStatus.ENABLE -> UserStatus.ENABLE.value
            UserStatus.LEAVE -> UserStatus.LEAVE.value
            UserStatus.FORCE_LEAVE -> UserStatus.FORCE_LEAVE.value
        }
    }

    override fun convertToEntityAttribute(dbData: String?): UserStatus? {
        return when (dbData) {
            UserStatus.ENABLE.value -> UserStatus.ENABLE
            UserStatus.LEAVE.value -> UserStatus.LEAVE
            UserStatus.FORCE_LEAVE.value -> UserStatus.FORCE_LEAVE
            else -> null
        }
    }
}

enum class UserType(val value: String) {
    USER("u"), CAST("c");
}

// DBとオブジェクト表現の変換用クラス
@Converter
class UserTypeConverter: AttributeConverter<UserType, String> {

    override fun convertToDatabaseColumn(attribute: UserType): String {
        return when (attribute) {
            UserType.USER -> UserType.USER.value
            UserType.CAST -> UserType.CAST.value
        }
    }

    override fun convertToEntityAttribute(dbData: String?): UserType? {
        return when (dbData) {
            UserType.USER.value -> UserType.USER
            UserType.CAST.value -> UserType.CAST
            else -> null
        }
    }
}