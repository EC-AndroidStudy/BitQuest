package com.largeblueberry.bitquest.feature_gemini.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.largeblueberry.bitquest.feature_gemini.AnalysisUiState
import com.largeblueberry.bitquest.feature_gemini.WrongAnswer
import com.largeblueberry.bitquest.feature_gemini.domain.GeminiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnalysisViewModel @Inject constructor(
    private val geminiRepository: GeminiRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<AnalysisUiState>(AnalysisUiState.Idle)
    val uiState: StateFlow<AnalysisUiState> = _uiState.asStateFlow()

    fun analyzeWrongAnswers(wrongAnswers: List<WrongAnswer>) {
        if (wrongAnswers.isEmpty()) {
            _uiState.value = AnalysisUiState.Error("분석할 오답이 없습니다")
            return
        }

        viewModelScope.launch {
            _uiState.value = AnalysisUiState.Loading

            geminiRepository.analyzeWrongAnswers(wrongAnswers)
                .onSuccess { result ->
                    _uiState.value = AnalysisUiState.Success(result)
                }
                .onFailure { exception ->
                    _uiState.value = AnalysisUiState.Error(
                        exception.message ?: "분석 중 오류가 발생했습니다"
                    )
                }
        }
    }

    fun resetState() {
        _uiState.value = AnalysisUiState.Idle
    }
}