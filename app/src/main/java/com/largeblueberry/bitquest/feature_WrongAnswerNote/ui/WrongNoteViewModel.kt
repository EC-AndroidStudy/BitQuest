package com.largeblueberry.bitquest.feature_WrongAnswerNote.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.model.WrongAnswerNote
import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.usecase.ObserveWrongAnswersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class WrongNoteViewModel @Inject constructor(
    observeWrongAnswers: ObserveWrongAnswersUseCase
) : ViewModel() {

    val notes: StateFlow<List<WrongAnswerNote>> =
        observeWrongAnswers().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
}