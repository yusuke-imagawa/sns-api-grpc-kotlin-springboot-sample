package com.talking.data.service

import com.talking.data.entity.Report
import com.talking.data.repository.ReportRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ReportService {

    @Autowired
    private lateinit var reportRepository: ReportRepository

    fun save(report: Report) : Report {
        return reportRepository.save(report)
    }
}