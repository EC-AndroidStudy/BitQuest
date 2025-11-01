package com.largeblueberry.bitquest.feature_FieldSelection.data

data class FieldEntity(
    val id: Int,
    val title: String,
    val description: String,
    val colorHex: String,
    val completedQuizCount: Int = 0,
    val totalQuizCount: Int = 10
)
