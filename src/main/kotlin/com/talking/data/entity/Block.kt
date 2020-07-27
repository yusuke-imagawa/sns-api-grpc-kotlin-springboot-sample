package com.talking.data.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "blocks")
data class Block(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "from_user_id")
    var fromUser: User? = null,

    @ManyToOne
    @JoinColumn(name = "block_user_id")
    var blockUser: User? = null
) {
    @Column(name = "created", nullable = false)
    @CreationTimestamp
    lateinit var created: LocalDateTime

    @Column(name = "modified", nullable = false)
    @UpdateTimestamp
    lateinit var modified: LocalDateTime
}