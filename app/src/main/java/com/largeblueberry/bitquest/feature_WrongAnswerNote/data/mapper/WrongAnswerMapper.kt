package com.largeblueberry.bitquest.feature_WrongAnswerNote.data.mapper

import com.largeblueberry.bitquest.feature_WrongAnswerNote.data.WrongAnswerEntity
import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.model.WrongAnswerNote

// Entity -> Domain Model
fun WrongAnswerEntity.toDomain(): WrongAnswerNote = WrongAnswerNote(
    id = id,
    questionId = questionId,
    title = title,
    questionText = questionText,
    selectedAnswer = selectedAnswer,
    correctAnswer = correctAnswer,
    category = category,
    createdAt = createdAt
)

// Domain Model -> Entity
fun WrongAnswerNote.toEntity(): WrongAnswerEntity = WrongAnswerEntity(
    id = id ?: 0,
    questionId = questionId,
    title = title,
    questionText = questionText,
    selectedAnswer = selectedAnswer,
    correctAnswer = correctAnswer,
    category = category,
    createdAt = createdAt
)
