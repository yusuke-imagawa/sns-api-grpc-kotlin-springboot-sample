package com.talking.api.grpc.converter

import com.google.protobuf.Timestamp
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

class CommonConverter {

    companion object {
        fun toGrpcTimestamp(date: LocalDateTime) : com.google.protobuf.Timestamp {

            val dateInstant = date.atZone(ZoneId.systemDefault()).toInstant()

            return com.google.protobuf.Timestamp.newBuilder()
                .setSeconds(dateInstant.epochSecond)
                .setNanos(dateInstant.nano).build()
        }

        fun toLocalDateTime(timestamp: Timestamp) : LocalDateTime {
            // https://stackoverflow.com/questions/52645487/how-to-convert-google-proto-timestamp-to-java-localdate
            return  Instant.ofEpochSecond(timestamp.seconds, timestamp.nanos.toLong())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()
        }
    }
}