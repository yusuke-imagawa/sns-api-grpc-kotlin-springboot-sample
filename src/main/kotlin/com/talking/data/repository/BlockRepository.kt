package com.talking.data.repository

import com.talking.data.entity.Block
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BlockRepository : JpaRepository<Block, Long> {
    fun findByFromUser_Id(fromId: Long) : List<Block>
}
