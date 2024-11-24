package com.wsr

import java.util.*

class Subject private constructor(
    val id: SubjectId,
    val userId: UserId,
    val content: SubjectContent,
) {
    companion object {
        fun create(userId: UserId, content: SubjectContent): Subject = Subject(
            id = SubjectId(UUID.randomUUID().toString()),
            userId = userId,
            content = content,
        )
    }
}

@JvmInline
value class SubjectId(val value: String)

@JvmInline
value class SubjectContent(val value: String)
