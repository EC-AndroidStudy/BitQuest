package com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.usecase

import com.largeblueberry.bitquest.feature_WrongAnswerNote.data.WrongAnswer
import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.model.WrongAnswerNote
import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.repository.WrongAnswerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

// domain/usecase/SaveWrongAnswerUseCase.kt
@Singleton
class SaveWrongAnswerUseCase @Inject constructor(
    private val repository: WrongAnswerRepository
) {
    suspend operator fun invoke(wrongAnswer: WrongAnswer) {
        repository.saveWrongAnswer(wrongAnswer)
    }
}