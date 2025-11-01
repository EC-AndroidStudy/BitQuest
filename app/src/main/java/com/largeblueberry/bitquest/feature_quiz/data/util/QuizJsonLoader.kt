package com.largeblueberry.bitquest.feature_quiz.data.util

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken // 🌟 TypeToken import 추가
import com.largeblueberry.bitquest.feature_quiz.data.QuizEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuizJsonLoader @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val TAG = "QuizJsonLoader"
    private val gson = Gson()

    fun loadQuizzesFromAssets(fileName: String = "quiz_data.json"): List<QuizEntity> {
        return try {
            Log.d(TAG, "Attempting to load file: $fileName")
            val jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            Log.d(TAG, "Successfully read ${jsonString.length} characters from file.")

            // 🌟 수정된 부분: TypeToken을 사용하여 List<QuizEntity>를 직접 파싱
            val listType = object : TypeToken<List<QuizEntity>>() {}.type
            val quizzes: List<QuizEntity> = gson.fromJson(jsonString, listType)

            Log.d(TAG, "Successfully parsed ${quizzes.size} quizzes.")
            quizzes // List<QuizEntity>를 직접 반환
        } catch (e: Exception) {
            // 💥 예외 발생 시 어떤 에러인지 명확하게 출력
            Log.e(TAG, "Error loading or parsing quiz data from assets: ${e.message}", e)
            e.printStackTrace()
            emptyList()
        }
    }
}