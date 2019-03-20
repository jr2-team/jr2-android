package ru.jjba.jr2.presentation.viewmodel.number.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.pnzeml.num2jp.getNumberInKanji
import io.github.pnzeml.num2jp.getNumberInKatakana
import ru.jjba.jr2.presentation.viewmodel.BaseViewModel
import ru.jjba.jr2.presentation.viewmodel.number.NumberPracticeQuiz
import kotlin.random.Random

class NumberPracticeQuizViewModel : BaseViewModel() {
    private val correctVariant = MutableLiveData<String>()
    private val variants = MutableLiveData<List<String>>()

    fun observeCorrectVariant(): LiveData<String> = correctVariant
    fun observeVariants(): LiveData<List<String>> = variants

    fun onNextQuizRequested(quiz: NumberPracticeQuiz) {
        decidePracticeType(quiz.question, quiz.variants)
    }

    private fun decidePracticeType(newCorrectVariant: Int, newVariants: List<Int>) {

        when (Random.nextInt(0, 6)) {
            0 -> {
                correctVariant.postValue(newCorrectVariant.toString())
                variants.postValue(newVariants.map { it.toString().getNumberInKanji() })
            }
            1 -> {
                correctVariant.postValue(newCorrectVariant.toString())
                variants.postValue(newVariants.map { it.toString().getNumberInKatakana() })
            }
            2 -> {
                correctVariant.postValue(newCorrectVariant.toString().getNumberInKanji())
                variants.postValue(newVariants.map { it.toString() })
            }
            3 -> {
                correctVariant.postValue(newCorrectVariant.toString().getNumberInKanji())
                variants.postValue(newVariants.map { it.toString().getNumberInKatakana() })
            }
            4 -> {
                correctVariant.postValue(newCorrectVariant.toString().getNumberInKatakana())
                variants.postValue(newVariants.map { it.toString() })
            }
            5 -> {
                correctVariant.postValue(newCorrectVariant.toString().getNumberInKatakana())
                variants.postValue(newVariants.map { it.toString().getNumberInKanji() })
            }
        }
    }
}