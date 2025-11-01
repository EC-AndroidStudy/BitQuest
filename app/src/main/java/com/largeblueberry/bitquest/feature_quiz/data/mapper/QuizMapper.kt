package com.largeblueberry.bitquest.feature_quiz.data.mapper

import com.largeblueberry.bitquest.feature_quiz.data.QuizEntity
import com.largeblueberry.bitquest.feature_quiz.data.QuizTypeEntity
import com.largeblueberry.bitquest.feature_quiz.domain.model.Quiz
import com.largeblueberry.bitquest.feature_quiz.domain.model.QuizType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuizMapper @Inject constructor() {

    fun mapToDomain(entity: QuizEntity?): Quiz {
        return Quiz(
            id = entity!!.id,
            question = entity.question,
            options = entity.options,
            correctAnswer = entity.correctAnswer,
            explanation = entity.explanation,
            category = entity.category,
            type = mapQuizTypeToDomain(entity.type)
        )
    }

    fun mapToEntity(domain: Quiz): QuizEntity {
        return QuizEntity(
            id = domain.id,
            question = domain.question,
            options = domain.options,
            correctAnswer = domain.correctAnswer,
            explanation = domain.explanation,
            category = domain.category,
            type = mapQuizTypeToEntity(domain.type)
        )
    }

    fun mapToDomainList(entities: List<QuizEntity>): List<Quiz> {
        return entities.map { mapToDomain(it) }
    }

    fun mapToEntityList(domains: List<Quiz>): List<QuizEntity> {
        return domains.map { mapToEntity(it) }
    }

    private fun mapQuizTypeToDomain(entityType: QuizTypeEntity): QuizType {
        return when (entityType) {
            QuizTypeEntity.OX -> QuizType.OX
            QuizTypeEntity.MULTIPLE_CHOICE -> QuizType.MULTIPLE_CHOICE
        }
    }

    private fun mapQuizTypeToEntity(domainType: QuizType): QuizTypeEntity {
        return when (domainType) {
            QuizType.OX -> QuizTypeEntity.OX
            QuizType.MULTIPLE_CHOICE -> QuizTypeEntity.MULTIPLE_CHOICE
        }
    }
}
