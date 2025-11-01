package com.largeblueberry.bitquest.feature_quiz.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "quiz")
data class QuizEntity(
    @PrimaryKey val id: Int,
    val question: String,
    val options: List<String>, // OX의 경우 ["O", "X"], 4지선다의 경우 4개 선택지
    val correctAnswer: Int, // 정답 인덱스 (0-based)
    val explanation: String,
    val category: String,
    val type: QuizTypeEntity // OX 또는 MULTIPLE_CHOICE
)