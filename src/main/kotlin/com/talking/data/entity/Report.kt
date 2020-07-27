package com.talking.data.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "reports")
data class Report(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "target_user_id")
    var targetUser: User = User(),

    @ManyToOne
    @JoinColumn(name = "reporter_user_id")
    var reporterUser: User = User(),

    @Lob
    var message: String? = null
) {
    @Column(name = "created", nullable = false)
    @CreationTimestamp
    lateinit var created: LocalDateTime

    @Column(name = "modified", nullable = false)
    @UpdateTimestamp
    lateinit var modified: LocalDateTime
}