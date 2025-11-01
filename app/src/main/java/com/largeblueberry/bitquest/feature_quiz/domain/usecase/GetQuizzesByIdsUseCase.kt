package com.largeblueberry.bitquest.feature_quiz.domain.usecase

import com.largeblueberry.bitquest.feature_quiz.domain.model.Quiz
import com.largeblueberry.bitquest.feature_quiz.domain.repository.QuizRepository
import javax.inject.Inject

class GetQuizzesByIdsUseCase @Inject constructor(
    private val repository: QuizRepository
) {
    suspend operator fun invoke(ids: List<Int>): List<Quiz> {
        return repository.getQuizzesByIds(ids)
    }
}