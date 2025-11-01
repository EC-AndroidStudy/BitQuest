package com.largeblueberry.bitquest.feature_quiz.domain.usecase

import com.largeblueberry.bitquest.feature_quiz.domain.QuizRepository
import com.largeblueberry.bitquest.feature_quiz.domain.model.Quiz
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetQuizByIdUseCase @Inject constructor(
    private val repository: QuizRepository
) {
    suspend operator fun invoke(id: Int): Quiz? {
        return repository.getQuizById(id)
    }
}