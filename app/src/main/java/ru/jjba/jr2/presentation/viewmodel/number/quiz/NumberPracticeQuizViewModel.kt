package ru.jjba.jr2.presentation.viewmodel.number.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.pnzeml.num2jp.getNumberInKanji
import ru.jjba.jr2.presentation.ui.number.PracticeNumberQuizType
import ru.jjba.jr2.presentation.ui.number.PracticeNumberSingleQuiz
import ru.jjba.jr2.presentation.viewmodel.base.BaseViewModel
import kotlin.random.Random

class NumberPracticeQuizViewModel : BaseViewModel() {
    private val question = MutableLiveData<String>()
    private val hint = MutableLiveData<String>()
    private val variants = MutableLiveData<List<String>>()

    private var currentQuiz: PracticeNumberSingleQuiz? = null

    fun observeQuestion(): LiveData<String> = question

    fun onNextQuiz(quiz: PracticeNumberSingleQuiz) {
        currentQuiz = quiz
        val generateVariants = sequence {
            yield(quiz.number.value)
            var nextNumber = Random.nextInt(1, 1000)
            while (nextNumber == quiz.number.value) {
                nextNumber = Random.nextInt(1, 1000)
            }
            yield(nextNumber)
            nextNumber = Random.nextInt(quiz.number.value - 5, quiz.number.value + 5)
            while (nextNumber == quiz.number.value) {
                nextNumber = Random.nextInt(quiz.number.value - 5, quiz.number.value + 5)
            }
            yield(nextNumber)
            yield(nextNumber * 10)
        }
        val generatedVariants = generateVariants.toMutableList().shuffled()
        when (/*quiz.quizType*/PracticeNumberQuizType.HIROGANA_KANJI) {
            PracticeNumberQuizType.HIROGANA_KANJI -> {
                question.postValue(quiz.number.numberHirogana)
                hint.value = quiz.number.valueStr
                variants.value = generatedVariants.map { it.toString().getNumberInKanji() }
            }
            PracticeNumberQuizType.HIROGANA_NUMBER -> TODO()
            PracticeNumberQuizType.KANJI_HIROGANA -> TODO()
            PracticeNumberQuizType.KANJI_NUMBER -> TODO()
            PracticeNumberQuizType.NUMBER_HIROGANA -> TODO()
            PracticeNumberQuizType.NUMBER_KANJI -> TODO()
        }
    }
}