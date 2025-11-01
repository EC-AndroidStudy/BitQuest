package com.largeblueberry.bitquest.feature_WrongAnswerNote.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.model.WrongAnswerNote
import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.usecase.AddWrongAnswerUseCase
import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.usecase.DeleteWrongAnswerUseCase
import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.usecase.GetAllWrongAnswersUseCase
import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.usecase.GetWrongAnswersByCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WrongNoteViewModel @Inject constructor(
    private val addWrongAnswerUseCase: AddWrongAnswerUseCase,
    private val getAllWrongAnswersUseCase: GetAllWrongAnswersUseCase,
    private val getWrongAnswersByCategoryUseCase: GetWrongAnswersByCategoryUseCase,
    private val deleteWrongAnswerUseCase: DeleteWrongAnswerUseCase
) : ViewModel() {

    private val _notes = MutableStateFlow<List<WrongAnswerNote>>(emptyList())
    val notes: StateFlow<List<WrongAnswerNote>> = _notes.asStateFlow()

    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory = _selectedCategory.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadAllNotes()
    }

    private fun loadAllNotes() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            getAllWrongAnswersUseCase()
                .catch { exception ->
                    _errorMessage.value = "오답노트를 불러오는 중 오류가 발생했습니다: ${exception.message}"
                    _notes.value = emptyList()
                }
                .collect { notesList ->
                    _notes.value = notesList
                    _isLoading.value = false
                }
        }
    }

    fun filterByCategory(category: String?) {
        viewModelScope.launch {
            _selectedCategory.value = category
            _isLoading.value = true
            _errorMessage.value = null

            try {
                if (category == null) {
                    getAllWrongAnswersUseCase()
                        .catch { exception ->
                            _errorMessage.value = "오답노트를 불러오는 중 오류가 발생했습니다: ${exception.message}"
                            _notes.value = emptyList()
                        }
                        .collect { notesList ->
                            _notes.value = notesList
                        }
                } else {
                    getWrongAnswersByCategoryUseCase(category)
                        .catch { exception ->
                            _errorMessage.value = "카테고리별 오답노트를 불러오는 중 오류가 발생했습니다: ${exception.message}"
                            _notes.value = emptyList()
                        }
                        .collect { notesList ->
                            _notes.value = notesList
                        }
                }
            } catch (exception: Exception) {
                _errorMessage.value = "필터링 중 오류가 발생했습니다: ${exception.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addWrongAnswer(wrongAnswer: WrongAnswerNote) {
        viewModelScope.launch {
            _errorMessage.value = null

            try {
                addWrongAnswerUseCase(wrongAnswer)
                // 추가 후 현재 필터에 따라 다시 로드
                filterByCategory(_selectedCategory.value)
            } catch (exception: Exception) {
                _errorMessage.value = "오답노트 추가 중 오류가 발생했습니다: ${exception.message}"
            }
        }
    }

    fun deleteNote(id: Int) {
        viewModelScope.launch {
            _errorMessage.value = null

            try {
                deleteWrongAnswerUseCase(id)
                // 삭제 후 현재 필터에 따라 다시 로드
                filterByCategory(_selectedCategory.value)
            } catch (exception: Exception) {
                _errorMessage.value = "오답노트 삭제 중 오류가 발생했습니다: ${exception.message}"
            }
        }
    }

    fun clearErrorMessage() {
        _errorMessage.value = null
    }
}