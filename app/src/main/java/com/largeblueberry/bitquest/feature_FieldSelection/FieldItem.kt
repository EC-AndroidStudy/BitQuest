package com.largeblueberry.bitquest.feature_FieldSelection

import androidx.compose.ui.graphics.Color

data class FieldItem(
    val id: Int,
    val title: String,
    val description: String,
    val backgroundColor: Color,
    val isLocked: Boolean = false
)
