package com.talking.data.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "calling_logs")
data class CallingLog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "from_user_id")
    var fromUser: User = User(),

    @ManyToOne
    @JoinColumn(name = "to_user_id")
    var toUser: User = User(),

    @Column(name = "status", nullable = false)
    @Convert(converter = CallingStatusConverter::class)
    var status: CallingStatus = CallingStatus.STARTING,

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_datetime")
    var startDatetime: Date? = null,

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_datetime")
    var endDatetime: Date? = null,

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_from_user_continue_datetime")
    var lastFromUserContinueDatetime: Date? = null,

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_to_user_continue_datetime")
    var lastToUserContinueDatetime: Date? = null
) {
    @Column(name = "created", nullable = false)
    @CreationTimestamp
    lateinit var created: LocalDateTime

    @Column(name = "modified", nullable = false)
    @UpdateTimestamp
    lateinit var modified: LocalDateTime
}

enum class CallingStatus(val value: String) {
    STARTING("starting"), TALKING("talking"), END("end");
}

@Converter
class CallingStatusConverter: AttributeConverter<CallingStatus, String> {

    override fun convertToDatabaseColumn(attribute: CallingStatus): String {
        return when (attribute) {
            CallingStatus.STARTING -> CallingStatus.STARTING.value
            CallingStatus.TALKING -> CallingStatus.TALKING.value
            CallingStatus.END -> CallingStatus.END.value
        }
    }

    override fun convertToEntityAttribute(dbData: String?): CallingStatus? {
        return when (dbData) {
            CallingStatus.STARTING.value -> CallingStatus.STARTING
            CallingStatus.TALKING.value -> CallingStatus.TALKING
            CallingStatus.END.value -> CallingStatus.END
            else -> null
        }
    }
}