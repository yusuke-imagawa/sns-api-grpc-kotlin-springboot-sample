package com.talking.data.repository

import com.talking.data.entity.CallingLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CallingLogRepository : JpaRepository<CallingLog, Long> {
}
