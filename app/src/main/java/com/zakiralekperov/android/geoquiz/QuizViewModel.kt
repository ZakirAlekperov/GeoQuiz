package com.zakiralekperov.android.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlin.math.abs

private const val TAG = "QuizViewModel"

class QuizViewModel: ViewModel() {

    val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )
    var currentIndex = 0
    var score = 0
    var flag = false

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext(){
        currentIndex = (currentIndex + 1) % questionBank.size
    }
    fun moveToBack(){
        if (currentIndex == 0 )
            currentIndex = questionBank.size - 1
        currentIndex = abs((currentIndex - 1) % questionBank.size)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel instance about to be destroyed")
    }
}