package com.wsr.comet

interface CometRepository {
    suspend fun getSubjects(limit: Int, offset: Int): List<Subject>
    suspend fun getReplyLists(subjectId: SubjectId, limit: Int, offset: Int): List<ReplyList>
    suspend fun createSubject(subject: Subject)
    suspend fun createReplyList(replyList: ReplyList)
}