package com.largeblueberry.bitquest.feature_WrongAnswerNote.data.mapper

import com.largeblueberry.bitquest.feature_WrongAnswerNote.data.WrongAnswerEntity
import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.model.WrongAnswerNote

fun WrongAnswerEntity.toDomain() = WrongAnswerNote(
    id = id,
    questionId = questionId,
    questionText = questionText,
    selectedAnswer = selectedAnswer,
    correctAnswer = correctAnswer,
    category = category,
    createdAt = createdAt
)

fun WrongAnswerNote.toEntity() = WrongAnswerEntity(
    id = id,
    questionId = questionId,
    questionText = questionText,
    selectedAnswer = selectedAnswer,
    correctAnswer = correctAnswer,
    category = category,
    createdAt = createdAt
)