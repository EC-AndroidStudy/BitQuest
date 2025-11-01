package com.largeblueberry.bitquest.feature_quiz.data.util

import android.content.Context
import com.google.gson.Gson
import com.largeblueberry.bitquest.feature_quiz.data.QuizEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

data class QuizDataWrapper(
    val quizzes: List<QuizEntity>
)

@Singleton
class QuizJsonLoader @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val gson = Gson()

    fun loadQuizzesFromAssets(fileName: String = "quiz_data.json"): List<QuizEntity> {
        return try {
            val jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            val wrapper = gson.fromJson(jsonString, QuizDataWrapper::class.java)
            wrapper.quizzes
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}