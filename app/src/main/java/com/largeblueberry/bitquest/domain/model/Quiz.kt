package com.largeblueberry.bitquest.domain.model

data class Quiz(
    val id: Int,
    val subject: String,
    val question: String,
    val answer: Boolean, // true for O, false for X
    val explanation: String
)
