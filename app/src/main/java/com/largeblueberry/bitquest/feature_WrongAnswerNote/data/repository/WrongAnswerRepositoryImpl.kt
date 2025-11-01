package com.largeblueberry.bitquest.feature_WrongAnswerNote.data.repository

import com.largeblueberry.bitquest.feature_WrongAnswerNote.data.WrongAnswerDao
import com.largeblueberry.bitquest.feature_WrongAnswerNote.data.mapper.toDomain
import com.largeblueberry.bitquest.feature_WrongAnswerNote.data.mapper.toEntity
import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.model.WrongAnswerNote
import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.repository.WrongAnswerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WrongAnswerRepositoryImpl @Inject constructor(
    private val dao: WrongAnswerDao
) : WrongAnswerRepository {

    override fun observeAll(): Flow<List<WrongAnswerNote>> =
        dao.observeAll().map { list -> list.map { it.toDomain() } }

    override suspend fun addAll(notes: List<WrongAnswerNote>) = withContext(Dispatchers.IO) {
        if (notes.isNotEmpty()) dao.insertAll(notes.map { it.toEntity() })
    }

    override suspend fun clear() = withContext(Dispatchers.IO) {
        dao.clear()
    }
}