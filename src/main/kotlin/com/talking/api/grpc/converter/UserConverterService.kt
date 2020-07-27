package com.talking.api.grpc.converter

import com.google.protobuf.StringValue
import com.talking.data.entity.GenderType
import com.talking.data.entity.ProfileImage
import com.talking.data.entity.User
import com.talking.data.service.ProfileImageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserConverterService {

    @Autowired
    private lateinit var profileImageService: ProfileImageService

    companion object {
        fun toDbGenderType(genderType: com.talking.grpc.common.GenderType) : GenderType {
            if (genderType == com.talking.grpc.common.GenderType.MALE) {
                return GenderType.MALE
            }
            return GenderType.FEMALE
        }

        fun toGrpcGenderType(genderType: GenderType) : com.talking.grpc.common.GenderType {
            if (genderType == GenderType.MALE) {
                return com.talking.grpc.common.GenderType.MALE
            }
            return com.talking.grpc.common.GenderType.FEMALE
        }
    }

    fun toGrpcUser(userEntity: User) : com.talking.grpc.common.User {
        val userBuilder = com.talking.grpc.common.User.newBuilder()
            .setId(userEntity.id)
            .setName(userEntity.name)
            .setGenderType(toGrpcGenderType(userEntity.genderType))
            .setAge(userEntity.age)
            .setPr(userEntity.pr)
            .setLastOnlineDatetime(CommonConverter.toGrpcTimestamp(userEntity.lastOnlineDatetime))
            .setCreated(CommonConverter.toGrpcTimestamp(userEntity.created))
            .setModified(CommonConverter.toGrpcTimestamp(userEntity.modified))
        return userBuilder.build()
    }
}