package com.wsr.comet

import com.wsr.user.UserId

data class Subject(
    val id: SubjectId,
    val userId: UserId,
    val content: Content,
)

@JvmInline
value class SubjectId(val value: String)
