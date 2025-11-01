package com.largeblueberry.bitquest.feature_WrongAnswerNote.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.largeblueberry.bitquest.feature_WrongAnswerNote.data.WrongAnswer
import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.usecase.ClearWrongAnswersUseCase
import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.usecase.GetWrongAnswersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WrongAnswerViewModel @Inject constructor(
    private val getWrongAnswersUseCase: GetWrongAnswersUseCase,
    private val clearWrongAnswersUseCase: ClearWrongAnswersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(WrongAnswerUiState())
    val uiState: StateFlow<WrongAnswerUiState> = _uiState.asStateFlow()

    fun loadWrongAnswers() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val wrongAnswers = getWrongAnswersUseCase()
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    wrongAnswers = wrongAnswers
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun clearAllWrongAnswers() {
        viewModelScope.launch {
            clearWrongAnswersUseCase()
            _uiState.value = _uiState.value.copy(wrongAnswers = emptyList())
        }
    }
}

// presentation/wronganswer/WrongAnswerUiState.kt
data class WrongAnswerUiState(
    val isLoading: Boolean = false,
    val wrongAnswers: List<WrongAnswer> = emptyList(),
    val error: String? = null
)
