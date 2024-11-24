package com.wsr.comet

import com.wsr.user.UserId

data class ReplyList(
    val subjectId: SubjectId,
    val userId: UserId,
    val contents: List<Reply>,
)

data class Reply(
    val id: ReplyId,
    val posterType: PosterType,
    val content: Content,
)

@JvmInline
value class ReplyId(val value: String)

sealed interface PosterType {
    data object Owner : PosterType
    data object Observer : PosterType
}
