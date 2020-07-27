package com.talking.data.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "push_token_ios")
data class PushTokenIos(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User? = null,

    @Column(name = "device_token")
    var deviceToken: String? = null
) {
    @Column(name = "created", nullable = false)
    @CreationTimestamp
    lateinit var created: LocalDateTime

    @Column(name = "modified", nullable = false)
    @UpdateTimestamp
    lateinit var modified: LocalDateTime
}