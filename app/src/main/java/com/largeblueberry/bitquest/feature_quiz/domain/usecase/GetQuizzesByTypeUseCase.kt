package com.largeblueberry.bitquest.feature_quiz.domain.usecase


import com.largeblueberry.bitquest.feature_quiz.domain.QuizRepository
import com.largeblueberry.bitquest.feature_quiz.domain.model.Quiz
import com.largeblueberry.bitquest.feature_quiz.domain.model.QuizType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetQuizzesByTypeUseCase @Inject constructor(
    private val repository: QuizRepository
) {
    suspend operator fun invoke(type: QuizType): List<Quiz> {
        return repository.getQuizzesByType(type)
    }
}