package com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.repository

import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.model.WrongAnswerNote
import kotlinx.coroutines.flow.Flow

interface WrongAnswerRepository {
    suspend fun insertWrongAnswer(wrongAnswer: WrongAnswerNote)
    suspend fun deleteWrongAnswer(id: Int)
    suspend fun getAllWrongAnswers(): Flow<List<WrongAnswerNote>>
    suspend fun getWrongAnswersByCategory(category: String): Flow<List<WrongAnswerNote>>
    suspend fun getWrongAnswerById(id: Int): WrongAnswerNote?
}