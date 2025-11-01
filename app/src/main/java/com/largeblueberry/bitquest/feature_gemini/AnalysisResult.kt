package com.largeblueberry.bitquest.feature_gemini

data class AnalysisResult(
    val weakAreas: List<String>,
    val recommendations: List<String>,
    val studyPlan: String,
    val overallScore: Int,
    val detailedFeedback: String
)
