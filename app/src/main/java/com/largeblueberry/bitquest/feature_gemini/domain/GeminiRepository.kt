package com.largeblueberry.bitquest.feature_gemini.domain

import com.largeblueberry.bitquest.feature_WrongAnswerNote.data.WrongAnswer
import com.largeblueberry.bitquest.feature_gemini.AnalysisResult


interface GeminiRepository {
    suspend fun analyzeWrongAnswers(wrongAnswers: List<WrongAnswer>): Result<AnalysisResult>
}