package com.talking.data.repository

import com.talking.data.entity.ProfileImage
import com.talking.data.entity.ProfileImageStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ProfileImageRepository : JpaRepository<ProfileImage, Long> {

    fun getByFileName(filename: String) : ProfileImage?

    fun findByUserIdAndStatus(userId: Long, status: ProfileImageStatus) : List<ProfileImage>
}
