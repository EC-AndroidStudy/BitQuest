package com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.repository

import com.largeblueberry.bitquest.feature_WrongAnswerNote.data.WrongAnswer
import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.model.WrongAnswerNote
import kotlinx.coroutines.flow.Flow

interface WrongAnswerRepository {
    suspend fun saveWrongAnswer(wrongAnswer: WrongAnswer)
    suspend fun getWrongAnswers(): List<WrongAnswer>
    suspend fun clearWrongAnswers()
}