package com.largeblueberry.bitquest.feature_ThemeSelection

import androidx.compose.ui.graphics.Color

data class ThemeItem(
    val id: Int,
    val title: String,
    val description: String,
    val backgroundColor: Color,
    val isLocked: Boolean = false
)
