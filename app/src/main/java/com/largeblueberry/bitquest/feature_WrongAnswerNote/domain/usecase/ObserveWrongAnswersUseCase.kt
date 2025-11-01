package com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.usecase

import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.model.WrongAnswerNote
import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.repository.WrongAnswerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveWrongAnswersUseCase @Inject constructor(
    private val repo: WrongAnswerRepository
) {
    operator fun invoke(): Flow<List<WrongAnswerNote>> = repo.observeAll()
}