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

    override suspend fun insertWrongAnswer(wrongAnswer: WrongAnswerNote) {
        dao.insertWrongAnswer(wrongAnswer.toEntity())
    }

    override suspend fun deleteWrongAnswer(id: Int) {
        dao.deleteWrongAnswerById(id)
    }

    override suspend fun getAllWrongAnswers(): Flow<List<WrongAnswerNote>> {
        return dao.getAllWrongAnswers().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getWrongAnswersByCategory(category: String): Flow<List<WrongAnswerNote>> {
        return dao.getWrongAnswersByCategory(category).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getWrongAnswerById(id: Int): WrongAnswerNote? {
        return dao.getWrongAnswerById(id)?.toDomain()
    }
}