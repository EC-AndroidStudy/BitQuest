package com.largeblueberry.bitquest.feature_WrongAnswerNote.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wrong_answers")
data class WrongAnswerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val questionId: Int,
    val title: String, // 이 줄을 다시 추가합니다.
    val questionText: String,
    val selectedAnswer: String,
    val correctAnswer: String,
    val category: String,
    val createdAt: Long
)