package com.largeblueberry.bitquest.feature_FieldSelection.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.largeblueberry.bitquest.feature_FieldSelection.domain.Field
import com.largeblueberry.bitquest.feature_FieldSelection.domain.GetFieldUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FieldSelectionViewModel @Inject constructor(
    private val getFieldUseCase: GetFieldUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(FieldSelectionUiState())
    val uiState: StateFlow<FieldSelectionUiState> = _uiState.asStateFlow()

    private val _navigationEvent = MutableSharedFlow<Int>()
    val navigationEvent: SharedFlow<Int> = _navigationEvent.asSharedFlow()

    init {
        loadField()
    }

    fun loadField() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                val field = getFieldUseCase()
                _uiState.value = _uiState.value.copy(
                    field = field,
                    isLoading = false,
                    errorMessage = null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "오류 발생"
                )
            }
        }
    }

    fun onFieldClick(field: Field) {
        viewModelScope.launch {
            _navigationEvent.emit(field.id)
        }
    }

    fun retryLoadThemes() {
        loadField()
    }
}