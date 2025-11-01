package com.largeblueberry.bitquest.feature_FieldSelection.domain

data class Field(
    val id: Int,
    val title: String,
    val description: String,
    val colorHex: String,
    val completedQuizCount: Int = 0,
    val totalQuizCount: Int = 10
)
