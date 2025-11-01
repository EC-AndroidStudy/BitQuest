package com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.usecase

import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.repository.WrongAnswerRepository
import javax.inject.Inject

class GetWrongAnswersByCategoryUseCase @Inject constructor(
    private val repository: WrongAnswerRepository
) {
    suspend operator fun invoke(category: String) =
        repository.getWrongAnswersByCategory(category)
}