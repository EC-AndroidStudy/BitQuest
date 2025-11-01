package com.largeblueberry.bitquest.feature_gemini

sealed class AnalysisUiState {
    object Idle : AnalysisUiState()
    object Loading : AnalysisUiState()
    data class Success(val result: AnalysisResult) : AnalysisUiState()
    data class Error(val message: String) : AnalysisUiState()
}
