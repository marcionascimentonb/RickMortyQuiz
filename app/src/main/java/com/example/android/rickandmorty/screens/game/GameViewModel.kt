package com.example.android.rickandmorty.screens.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.android.rickandmorty.R

data class Question(
    var questionID: Int,
    var answer: Boolean,
    var attempted: Boolean = false,
    var answered: Boolean = false
)

class GameViewModel : ViewModel() {
    private var questionIndex = 0
    private lateinit var questionBank: MutableList<Question>

    private val _scoreString = MutableLiveData<String>()
    val scoreString: LiveData<String>
        get() = _scoreString

    //current attempt
    private val _questionId = MutableLiveData<Int>()
    val questionId: LiveData<Int>
        get() = _questionId

    private val _attempted = MutableLiveData<Boolean>()
    val attempted: LiveData<Boolean>
        get() = _attempted

    private val _checkTrue = MutableLiveData<Boolean>()
    val checkTrue: LiveData<Boolean>
        get() = _checkTrue

    private val _checkFalse = MutableLiveData<Boolean>()
    val checkFalse: LiveData<Boolean>
        get() = _checkFalse

    private val _isCorrect = MutableLiveData<Boolean>()
    val isCorrect: LiveData<Boolean>
        get() = _isCorrect

    //Event which triggers the end of the game
    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish


    init{
        newGame()
    }

    fun onGameFinish() {
        _eventGameFinish.value = true
    }

    /** Method for the game completed event **/
    fun onGameFinishComplete(){
        _eventGameFinish.value = false
    }

    fun questionAttempted() = questionBank.count { it.attempted }

    fun newGame(){
        resetQuestionBank()
        questionIndex = 0
        _eventGameFinish.value = false
        updateQuestion()
    }

    private fun updateQuestion(){

        _questionId.value = questionBank[questionIndex].questionID
        _attempted.value = questionBank[questionIndex].attempted
        _isCorrect.value = questionBank[questionIndex].answer == questionBank[questionIndex].answered

        _checkFalse.value = questionBank[questionIndex].attempted && questionBank[questionIndex].answered == false
        _checkTrue.value = questionBank[questionIndex].attempted && questionBank[questionIndex].answered == true

        _scoreString.value = "Your Score: ${questionBank.count {it.answer == it.answered && it.attempted}}/${questionBank.size}"

        if(questionAttempted() == questionBank.size){
            onGameFinish()
        }
    }

    fun prev(){
        goNextQuestion("desc")
        updateQuestion()
    }

    fun next(){
        goNextQuestion()
        updateQuestion()
    }

    fun onSelected(choice:Boolean){
        when (choice){
            true -> {
              _checkTrue.value = true
                questionBank[questionIndex].answered = true
            } else->{
               _checkFalse.value = true
            questionBank[questionIndex].answered = false
             }
        }
        _attempted.value = true
        questionBank[questionIndex].attempted = true
        updateQuestion()
    }

    private fun goNextQuestion(direction:String = "asc"){
        if (questionIndex == 0 && direction.equals("desc")) {
              questionIndex = questionBank.count() - 1
        }else if (questionIndex == questionBank.count()-1 && direction.equals("asc")){
             questionIndex = 0
        }else {
                if(direction.equals("desc"))
                    questionIndex--
                else
                    questionIndex++
        }
    }

    private fun resetQuestionBank(){
        questionBank = mutableListOf(
            Question(R.string.question_1, false),
            Question(R.string.question_2, true),
            Question(R.string.question_3, true),
            Question(R.string.question_4, false),
            Question(R.string.question_5, false),
            Question(R.string.question_6, true),
            Question(R.string.question_7, false),
            Question(R.string.question_8, true),
            Question(R.string.question_9, false),
            Question(R.string.question_10, false),
            Question(R.string.question_11, false),
            Question(R.string.question_12, true),
            Question(R.string.question_13, false),
            Question(R.string.question_14, true),
            Question(R.string.question_15, false),
            Question(R.string.question_16, false),
            Question(R.string.question_17, true),
            Question(R.string.question_18, false),
            Question(R.string.question_19, false),
            Question(R.string.question_20, true)
        )
    }
}