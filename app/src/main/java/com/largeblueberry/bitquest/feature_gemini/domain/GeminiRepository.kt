package com.largeblueberry.bitquest.feature_gemini.domain

import com.largeblueberry.bitquest.feature_gemini.AnalysisResult
import com.largeblueberry.bitquest.feature_gemini.WrongAnswer

interface GeminiRepository {
    suspend fun analyzeWrongAnswers(wrongAnswers: List<WrongAnswer>): Result<AnalysisResult>
}