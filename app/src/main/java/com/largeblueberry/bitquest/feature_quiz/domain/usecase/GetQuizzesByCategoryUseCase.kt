package com.largeblueberry.bitquest.feature_quiz.domain.usecase

import com.largeblueberry.bitquest.feature_quiz.domain.QuizRepository
import com.largeblueberry.bitquest.feature_quiz.domain.model.Quiz
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetQuizzesByCategoryUseCase @Inject constructor(
    private val repository: QuizRepository
) {
    suspend operator fun invoke(category: String): List<Quiz> {
        return repository.getQuizzesByCategory(category)
    }
}