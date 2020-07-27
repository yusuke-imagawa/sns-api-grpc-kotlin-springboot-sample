package com.talking.data.service

import com.talking.data.entity.ProfileImage
import com.talking.data.entity.ProfileImageStatus
import com.talking.data.repository.ProfileImageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class ProfileImageService {

    @Autowired
    private lateinit var profileImageRepository: ProfileImageRepository

    fun save(profileImage: ProfileImage) : ProfileImage {
        return profileImageRepository.save(profileImage)
    }

    /**
     * 指定されたidのレコードを削除します
     * @param id
     */
    fun delete(id: Long) {
        profileImageRepository.deleteById(id)
    }

    /**
     * 指定したユーザーidのレコードを論理削除します
     * @param userId
     */
    fun logicalDelete(userId: Long) {
        val profileImages = profileImageRepository.findByUserIdAndStatus(userId, ProfileImageStatus.ENABLE)
        for (profileImage in profileImages) {
            profileImage.status = ProfileImageStatus.DELETED_BY_USER
            save(profileImage)
        }
    }

    /**
     * 有効なプロフィール画像を返します。
     */
    fun getEnableProfileImage(userId: Long) : ProfileImage? {
        return profileImageRepository.findByUserIdAndStatus(userId, ProfileImageStatus.ENABLE).firstOrNull()
    }

    /**
     * 使われていないファイル名をUUIDから生成します。
     * @return String
     */
    fun generateUniqueUUIDFilename(fileType: String) : String? {
        var filename: String? = null
        for (i in 1..10) {
            // name: user + 9桁の数値
            filename = UUID.randomUUID().toString() + "." + fileType
            profileImageRepository.getByFileName(filename) ?: break
        }
        return filename
    }
}