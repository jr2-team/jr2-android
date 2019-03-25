package ru.jjba.jr2.presentation.ui.number.viewmodel.quiz

import androidx.lifecycle.MutableLiveData
import io.github.pnzeml.num2jp.getNumberInKanji
import ru.jjba.jr2.presentation.ui.number.data.NumberPracticeSessionRepository
import ru.jjba.jr2.presentation.ui.number.domani.JpNumber
import ru.jjba.jr2.presentation.ui.number.domani.JpNumberQuiz
import ru.jjba.jr2.presentation.ui.number.domani.JpNumberQuizType
import ru.jjba.jr2.presentation.viewmodel.base.BaseViewModel
import kotlin.random.Random

class NumberPracticeQuizViewModel(
    private val sessionRep: NumberPracticeSessionRepository
) : BaseViewModel() {
    val questionLiveData = MutableLiveData<String>()
    val hintLiveData = MutableLiveData<String>()
    val variantsLiveData = MutableLiveData<List<String>>()

    private lateinit var currentJpNumberQuiz: JpNumberQuiz

    fun onNextQuiz() {
        currentJpNumberQuiz =
            sessionRep.getNextQuiz() ?: JpNumberQuiz(JpNumber(1), JpNumberQuizType.HIROGANA_KANJI)
        processQuestion()
    }

    private fun processQuestion() {
        // TODO: Implement variants generation
        val generateVariants = sequence {
            yield(currentJpNumberQuiz.number.value)
            var nextNumber = Random.nextInt(1, 1000)
            while (nextNumber == currentJpNumberQuiz.number.value) {
                nextNumber = Random.nextInt(1, 1000)
            }
            yield(nextNumber)
            nextNumber = Random.nextInt(
                currentJpNumberQuiz.number.value - 5,
                currentJpNumberQuiz.number.value + 5
            )
            while (nextNumber == currentJpNumberQuiz.number.value) {
                nextNumber = Random.nextInt(
                    currentJpNumberQuiz.number.value - 5,
                    currentJpNumberQuiz.number.value + 5
                )
            }
            yield(nextNumber)
            yield(nextNumber * 10)
        }

        val generatedVariants = generateVariants.toMutableList().shuffled()
        when (/*currentJpNumberQuiz.quizType*/JpNumberQuizType.HIROGANA_KANJI) {
            JpNumberQuizType.HIROGANA_KANJI -> {
                questionLiveData.postValue(currentJpNumberQuiz.number.numberHirogana)
                hintLiveData.postValue(currentJpNumberQuiz.number.valueStr)
                variantsLiveData.postValue(
                    generatedVariants.map { it.toString().getNumberInKanji() }
                )
            }
            JpNumberQuizType.HIROGANA_NUMBER -> TODO()
            JpNumberQuizType.KANJI_HIROGANA -> TODO()
            JpNumberQuizType.KANJI_NUMBER -> TODO()
            JpNumberQuizType.NUMBER_HIROGANA -> TODO()
            JpNumberQuizType.NUMBER_KANJI -> TODO()
        }
    }
}