package com.largeblueberry.bitquest.feature_WrongAnswerNote.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.largeblueberry.bitquest.feature_WrongAnswerNote.data.WrongAnswer
import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.repository.WrongAnswerRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.toMutableList
import androidx.core.content.edit

@Singleton
class WrongAnswerRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : WrongAnswerRepository {

    private val sharedPrefs = context.getSharedPreferences("wrong_answers", Context.MODE_PRIVATE)
    private val gson = Gson()

    override suspend fun saveWrongAnswer(wrongAnswer: WrongAnswer) = withContext(Dispatchers.IO) {
        val currentList = getWrongAnswers().toMutableList()
        currentList.add(wrongAnswer)

        val json = gson.toJson(currentList)
        sharedPrefs.edit {putString("wrong_answers_list", json)}
    }

    override suspend fun getWrongAnswers(): List<WrongAnswer> = withContext(Dispatchers.IO) {
        val json = sharedPrefs.getString("wrong_answers_list", null)
        if (json != null) {
            val type = object : TypeToken<List<WrongAnswer>>() {}.type
            gson.fromJson(json, type)
        } else {
            emptyList()
        }
    }

    override suspend fun clearWrongAnswers() = withContext(Dispatchers.IO) {
        sharedPrefs.edit { remove("wrong_answers_list") }
    }
}
