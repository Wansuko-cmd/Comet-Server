package com.wsr.comet

import com.wsr.UserId
import java.util.*

class Subject private constructor(
    val id: SubjectId,
    val userId: UserId,
    val content: Content,
) {
    companion object {
        fun create(userId: UserId, content: Content): Subject = Subject(
            id = SubjectId(UUID.randomUUID().toString()),
            userId = userId,
            content = content,
        )
    }
}

@JvmInline
value class SubjectId(val value: String)
