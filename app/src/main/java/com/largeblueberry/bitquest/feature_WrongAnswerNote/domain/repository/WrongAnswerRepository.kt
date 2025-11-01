package com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.repository

import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.model.WrongAnswerNote
import kotlinx.coroutines.flow.Flow

interface WrongAnswerRepository {
    fun observeAll(): Flow<List<WrongAnswerNote>>
    suspend fun addAll(notes: List<WrongAnswerNote>)
    suspend fun clear()
}