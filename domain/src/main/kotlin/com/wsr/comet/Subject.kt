package com.wsr.comet

import com.wsr.user.UserId
import java.util.*

data class Subject(
    val id: SubjectId = SubjectId(UUID.randomUUID().toString()),
    val userId: UserId,
    val content: Content,
)

@JvmInline
value class SubjectId(val value: String)
