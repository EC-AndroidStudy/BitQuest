package com.largeblueberry.bitquest.feature_WrongAnswerNote.data.local

import android.content.SharedPreferences
import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.model.WrongAnswerNote
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.json.JSONArray
import org.json.JSONObject

private const val KEY_WRONG_NOTES = "wrong_notes"

class WrongAnswerLocalStore(
    private val prefs: SharedPreferences
) {
    private val _flow = MutableStateFlow(readAll())
    val flow = _flow.asStateFlow()

    fun addAll(list: List<WrongAnswerNote>) {
        if (list.isEmpty()) return
        val current = readAll().toMutableList()

        var nextId = (current.maxOfOrNull { it.id } ?: 0) + 1
        val withIds = list.map { if (it.id == 0) it.copy(id = nextId++) else it }

        current.addAll(withIds)
        writeAll(current)
        _flow.value = current
    }

    fun clear() {
        prefs.edit().remove(KEY_WRONG_NOTES).apply()
        _flow.value = emptyList()
    }

    private fun readAll(): List<WrongAnswerNote> {
        val raw = prefs.getString(KEY_WRONG_NOTES, null) ?: return emptyList()
        val arr = JSONArray(raw)
        val out = ArrayList<WrongAnswerNote>(arr.length())
        for (i in 0 until arr.length()) {
            val o = arr.getJSONObject(i)
            out.add(
                WrongAnswerNote(
                    id = o.optInt("id", 0),
                    questionId = o.getInt("questionId"),
                    questionText = o.getString("questionText"),
                    selectedAnswer = o.getString("selectedAnswer"),
                    correctAnswer = o.getString("correctAnswer"),
                    category = o.getString("category"),
                    createdAt = o.optLong("createdAt", System.currentTimeMillis())
                )
            )
        }
        return out
    }

    private fun writeAll(list: List<WrongAnswerNote>) {
        val arr = JSONArray()
        list.forEach { n ->
            val o = JSONObject().apply {
                put("id", n.id)
                put("questionId", n.questionId)
                put("questionText", n.questionText)
                put("selectedAnswer", n.selectedAnswer)
                put("correctAnswer", n.correctAnswer)
                put("category", n.category)
                put("createdAt", n.createdAt)
            }
            arr.put(o)
        }
        prefs.edit().putString(KEY_WRONG_NOTES, arr.toString()).apply()
    }
}