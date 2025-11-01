package com.largeblueberry.bitquest.feature_FieldSelection.ui

import com.largeblueberry.bitquest.feature_FieldSelection.domain.Field

data class FieldSelectionUiState(
    val field: List<Field> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
