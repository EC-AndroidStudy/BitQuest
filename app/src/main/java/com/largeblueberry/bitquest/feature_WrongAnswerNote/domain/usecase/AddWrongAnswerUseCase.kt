package com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.usecase

import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.model.WrongAnswerNote
import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.repository.WrongAnswerRepository
import javax.inject.Inject

class AddWrongAnswerUseCase @Inject constructor(
    private val repository: WrongAnswerRepository
) {
    suspend operator fun invoke(wrongAnswer: WrongAnswerNote) {
        repository.insertWrongAnswer(wrongAnswer)
    }
}