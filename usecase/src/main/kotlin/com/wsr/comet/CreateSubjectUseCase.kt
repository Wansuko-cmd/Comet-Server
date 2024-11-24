package com.wsr.comet

import com.wsr.user.UserId

class CreateSubjectUseCase(
    private val cometRepository: CometRepository,
) {
    suspend operator fun invoke(
        userId: UserId,
        content: Content,
    ) {
        val subject = Subject(
            userId = userId,
            content = content,
        )
        cometRepository.createSubject(subject)
    }
}