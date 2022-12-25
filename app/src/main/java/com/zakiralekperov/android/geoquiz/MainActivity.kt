package com.zakiralekperov.android.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.zakiralekperov.android.geoquiz.databinding.ActivityMainBinding
import kotlin.math.abs


private const val TAG = "MainActivity"


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var trueButton: Button
    private lateinit var falseButton:Button
    private lateinit var nextButton: Button
    private lateinit var prevButton: Button
    private lateinit var questionText: TextView
    private lateinit var mainLayout: LinearLayout

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )
    private var currentIndex = 0
    private var score = 0
    private var flag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        trueButton = binding.trueButton
        falseButton = binding.falseButton
        nextButton = binding.nextButton
        prevButton = binding.prevButton
        questionText = binding.questionTextView
        mainLayout = binding.mainLayout

        trueButton.setOnClickListener{
            checkAnswer(true)
            answerButtonClickable(false)
        }

        falseButton.setOnClickListener{
            checkAnswer(false)
            answerButtonClickable(false)
        }

        nextButton.setOnClickListener {
            if ((currentIndex == questionBank.size -1 ) and flag){
                scoreScreen()
            }
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
            answerButtonClickable(true)
            flag = true
        }

        questionText.setOnClickListener{
            if ((currentIndex ==questionBank.size -1) and flag){
                scoreScreen()
            }
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
            answerButtonClickable(true)
            flag = true
        }

        prevButton.setOnClickListener{
            if (currentIndex == 0 )
                currentIndex = questionBank.size - 1
            currentIndex = abs( (currentIndex - 1) % questionBank.size)
            updateQuestion()
            answerButtonClickable(true)
        }

        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion(){
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }

    private  fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId: Int
        if(userAnswer == correctAnswer){
            messageResId = R.string.correct_toast
            score +=1
        }else{
            messageResId = R.string.incorrect_toast
        }

        Snackbar.make(mainLayout, messageResId, Snackbar.LENGTH_SHORT).show()
    }

    private fun answerButtonClickable(isClickable: Boolean){
        trueButton.isClickable = isClickable
        falseButton.isClickable = isClickable
    }

    private fun scoreScreen(){
        val string = getString(R.string.your_score) + score.toString()
        Snackbar.make(mainLayout, string, Snackbar.LENGTH_SHORT ).show()
    }
}