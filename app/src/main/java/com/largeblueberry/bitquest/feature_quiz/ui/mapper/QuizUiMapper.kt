package com.largeblueberry.bitquest.feature_quiz.ui.mapper

import com.largeblueberry.bitquest.feature_quiz.domain.model.Quiz
import com.largeblueberry.bitquest.feature_quiz.domain.model.QuizResult
import com.largeblueberry.bitquest.feature_quiz.domain.model.QuizType
import com.largeblueberry.bitquest.feature_quiz.ui.model.QuizResultUiModel
import com.largeblueberry.bitquest.feature_quiz.ui.model.QuizTypeUi
import com.largeblueberry.bitquest.feature_quiz.ui.model.QuizUiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuizUiMapper @Inject constructor() {

    fun mapToUiModel(domain: Quiz): QuizUiModel {
        return QuizUiModel(
            id = domain.id,
            question = domain.question,
            options = domain.options,
            correctAnswer = domain.correctAnswer,
            explanation = domain.explanation,
            category = domain.category,
            type = mapQuizTypeToUi(domain.type)
        )
    }

    fun mapToDomain(uiModel: QuizUiModel): Quiz {
        return Quiz(
            id = uiModel.id,
            question = uiModel.question,
            options = uiModel.options,
            correctAnswer = uiModel.correctAnswer,
            explanation = uiModel.explanation,
            category = uiModel.category,
            type = mapQuizTypeToDomain(uiModel.type)
        )
    }

    fun mapToUiModelList(domains: List<Quiz>): List<QuizUiModel> {
        return domains.map { mapToUiModel(it) }
    }

    fun mapResultToUiModel(domainResult: QuizResult): QuizResultUiModel {
        return QuizResultUiModel(
            quiz = mapToUiModel(domainResult.quiz),
            selectedAnswer = domainResult.selectedAnswer,
            isCorrect = domainResult.isCorrect,
            timeSpent = domainResult.timeSpent
        )
    }

    fun mapQuizTypeToUi(domainType: QuizType): QuizTypeUi {
        return when (domainType) {
            QuizType.OX -> QuizTypeUi.OX
            QuizType.MULTIPLE_CHOICE -> QuizTypeUi.MULTIPLE_CHOICE
        }
    }

    fun mapQuizTypeToDomain(uiType: QuizTypeUi): QuizType {
        return when (uiType) {
            QuizTypeUi.OX -> QuizType.OX
            QuizTypeUi.MULTIPLE_CHOICE -> QuizType.MULTIPLE_CHOICE
        }
    }
}
