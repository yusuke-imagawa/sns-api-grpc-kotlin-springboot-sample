package com.talking.data.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "chat_messages")
data class ChatMessage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "from_user_id")
    var fromUser: User = User(),

    @ManyToOne
    @JoinColumn(name = "to_user_id")
    var toUser: User = User(),

    @Lob
    var message: String = ""
) {
    @Column(name = "created", nullable = false)
    @CreationTimestamp
    lateinit var created: LocalDateTime

    @Column(name = "modified", nullable = false)
    @UpdateTimestamp
    lateinit var modified: LocalDateTime
}