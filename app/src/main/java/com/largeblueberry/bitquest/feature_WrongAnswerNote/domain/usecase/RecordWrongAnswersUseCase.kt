package com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.usecase

import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.model.WrongAnswerNote
import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.repository.WrongAnswerRepository
import javax.inject.Inject

class RecordWrongAnswersUseCase @Inject constructor(
    private val repo: WrongAnswerRepository
) {
    suspend operator fun invoke(items: List<WrongAnswerNote>) = repo.addAll(items)
}