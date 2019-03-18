package ru.jjba.jr2.presentation.viewmodel.number

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.pnzeml.num2jp.getNumberInKanji
import io.github.pnzeml.num2jp.getNumberInKatakana
import ru.jjba.jr2.presentation.viewmodel.BaseViewModel
import ru.jjba.jr2.presentation.viewmodel.util.defaultValue
import kotlin.random.Random

class NumberPracticeViewModel : BaseViewModel() {
    // Settings
    private val genFromValue = 0
    private val genToValue = 1000

    private val correctVariant = MutableLiveData<String>()
    private val variants = MutableLiveData<List<String>>()

    private val correctVariantFromVariants = MutableLiveData<String?>().defaultValue(null)

    private var questionType = QuestionType.HIROGANA_KANJI
    // TODO: Replace with something more adequate
    private var isAnswerRevealed = false
    private var correctNumber = String()
    private var correctKanji = String()
    private var correctHirogana = String()

    init {
        getNextTest()
    }

    fun observeCorrectVariant(): LiveData<String> = correctVariant
    fun observeVariants(): LiveData<List<String>> = variants

    fun observeCorrectVariantFromVariants(): LiveData<String?> = correctVariantFromVariants

    fun onAnswerClicked(givenAnswer: String) {
        correctVariantFromVariants.value = when (questionType) {
            QuestionType.HIROGANA_KANJI, QuestionType.NUMBER_KANJI -> {
                correctKanji
            }
            QuestionType.KANJI_HIROGANA, QuestionType.NUMBER_HIROGANA -> {
                correctHirogana
            }
            QuestionType.HIROGANA_NUMBER, QuestionType.KANJI_NUMBER -> {
                correctNumber
            }
        }
    }

    fun onNextClicked() {
        if (isAnswerRevealed) getNextTest()
    }

    private fun getNextTest() {
        val newCorrectVariant = Random.nextInt(genFromValue, genToValue)
        val newVariants = mutableListOf<Int>()
        // add correct variant
        newVariants.add(newCorrectVariant)
        // add correct variant -/+ 5
        newVariants.add((Random.nextInt(newCorrectVariant - 5, newCorrectVariant + 5)))
        // add correct variant * 10
        newVariants.add((newCorrectVariant * 10))
        // add completely different variant from correct
        var rndInt = Random.nextInt(genFromValue, genToValue)
        while (rndInt == newCorrectVariant) {
            rndInt = Random.nextInt(genFromValue, genToValue)
        }
        newVariants.add(rndInt)
        newVariants.shuffle()
        decidePracticeType(newCorrectVariant, newVariants)

        correctNumber = newCorrectVariant.toString()
        correctKanji = correctNumber.getNumberInKanji()
        correctHirogana = correctNumber.getNumberInKatakana()
    }

    private fun decidePracticeType(newCorrectVariant: Int, newVariants: List<Int>) {
        questionType = QuestionType.fromCode(Random.nextInt(6))

        when (questionType) {
            QuestionType.NUMBER_KANJI -> {
                correctVariant.postValue(newCorrectVariant.toString())
                variants.postValue(newVariants.map { it.toString().getNumberInKanji() })
            }
            QuestionType.NUMBER_HIROGANA -> {
                correctVariant.postValue(newCorrectVariant.toString())
                variants.postValue(newVariants.map { it.toString().getNumberInKatakana() })
            }
            QuestionType.KANJI_NUMBER -> {
                correctVariant.postValue(newCorrectVariant.toString().getNumberInKanji())
                variants.postValue(newVariants.map { it.toString() })
            }
            QuestionType.KANJI_HIROGANA -> {
                correctVariant.postValue(newCorrectVariant.toString().getNumberInKanji())
                variants.postValue(newVariants.map { it.toString().getNumberInKatakana() })
            }
            QuestionType.HIROGANA_NUMBER -> {
                correctVariant.postValue(newCorrectVariant.toString().getNumberInKatakana())
                variants.postValue(newVariants.map { it.toString() })
            }
            QuestionType.HIROGANA_KANJI -> {
                correctVariant.postValue(newCorrectVariant.toString().getNumberInKatakana())
                variants.postValue(newVariants.map { it.toString().getNumberInKanji() })
            }
        }
    }

    enum class QuestionType(val code: Int) {
        NUMBER_KANJI(0),
        NUMBER_HIROGANA(1),
        KANJI_NUMBER(2),
        KANJI_HIROGANA(3),
        HIROGANA_NUMBER(4),
        HIROGANA_KANJI(5);

        companion object {
            fun fromCode(code: Int): QuestionType {
                return when (code) {
                    NUMBER_KANJI.code -> NUMBER_KANJI
                    NUMBER_HIROGANA.code -> NUMBER_HIROGANA
                    KANJI_NUMBER.code -> KANJI_NUMBER
                    KANJI_HIROGANA.code -> KANJI_HIROGANA
                    HIROGANA_NUMBER.code -> HIROGANA_NUMBER
                    HIROGANA_KANJI.code -> HIROGANA_KANJI
                    else -> throw IllegalArgumentException()
                }
            }
        }
    }
}