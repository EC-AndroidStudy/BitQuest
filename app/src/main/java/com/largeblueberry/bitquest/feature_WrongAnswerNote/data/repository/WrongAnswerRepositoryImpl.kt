package com.largeblueberry.bitquest.feature_WrongAnswerNote.data.repository

import com.largeblueberry.bitquest.feature_WrongAnswerNote.data.WrongAnswerDao
import com.largeblueberry.bitquest.feature_WrongAnswerNote.data.mapper.toDomain
import com.largeblueberry.bitquest.feature_WrongAnswerNote.data.mapper.toEntity
import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.model.WrongAnswerNote
import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.repository.WrongAnswerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WrongAnswerRepositoryImpl @Inject constructor(
    private val dao: WrongAnswerDao
) : WrongAnswerRepository {

    override fun observeAll(): Flow<List<WrongAnswerNote>> {
        return dao.observeAll().map { entities ->
            entities.map { it.toDomain() } // 확장 함수 사용
        }
    }

    override suspend fun addAll(notes: List<WrongAnswerNote>) {
        if (notes.isNotEmpty()) {
            dao.insertAll(notes.map { it.toEntity() }) // 확장 함수 사용
        }
    }

    override suspend fun clear() {
        dao.clear()
    }
}