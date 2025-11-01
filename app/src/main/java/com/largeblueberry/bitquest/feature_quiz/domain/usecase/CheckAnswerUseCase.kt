package com.largeblueberry.bitquest.feature_quiz.domain.usecase


import com.largeblueberry.bitquest.feature_quiz.domain.model.Quiz
import com.largeblueberry.bitquest.feature_quiz.domain.model.QuizResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CheckAnswerUseCase @Inject constructor() {
    operator fun invoke(quiz: Quiz, selectedAnswer: Int): QuizResult {
        val isCorrect = selectedAnswer == quiz.correctAnswer
        return QuizResult(
            quiz = quiz,
            selectedAnswer = selectedAnswer,
            isCorrect = isCorrect
        )
    }
}