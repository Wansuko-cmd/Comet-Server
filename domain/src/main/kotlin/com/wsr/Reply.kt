package com.wsr

class Replies(
    val subjectId: SubjectId,
    val userId: UserId,
    val contents: List<Reply>,
)

class Reply(
    val id: ReplyId,
    val posterType: PosterType,
    val content: ReplyContent,
)

@JvmInline
value class ReplyId(val value: String)

sealed interface PosterType {
    data object Owner : PosterType
    data object Observer : PosterType
}

@JvmInline
value class ReplyContent(val value: String)
