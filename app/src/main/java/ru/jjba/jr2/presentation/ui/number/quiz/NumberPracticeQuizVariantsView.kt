package ru.jjba.jr2.presentation.ui.number.quiz

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.android.synthetic.main.component_number_practice_variants.view.*
import org.jetbrains.anko.collections.forEachWithIndex
import org.jetbrains.anko.sdk25.coroutines.onClick
import ru.jjba.jr2.R

class NumberPracticeQuizVariantsView : ConstraintLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val layoutRes = R.layout.component_number_practice_variants
    private val btnVariants: List<Button>
    private val givenAnswer = MutableLiveData<String>()
    private var isAnswerRevealed = false

    init {
        val view = LayoutInflater.from(context).inflate(layoutRes, this, false)
        addView(view)
        btnVariants = listOf(btnVariantA, btnVariantB, btnVariantC, btnVariantD)
    }

    fun setAnswers(variants: List<String>) {
        if (variants.count() != 4) throw IllegalArgumentException()
        clear()
        btnVariants.forEachWithIndex { idx, button ->
            button.text = variants[idx]
            button.onClick { if (!isAnswerRevealed) givenAnswer.postValue(variants[idx]) }
        }
    }

    fun observeGivenAnswer(): LiveData<String> = givenAnswer

    fun revealCorrectVariant(correctVariant: String) {
        isAnswerRevealed = true
        btnVariants.forEach {
            if (it.text == correctVariant) it.setBackgroundColor(Color.GREEN)
        }

        if (correctVariant != givenAnswer.value) {
            btnVariants.forEach {
                if (it.text == givenAnswer.value) it.setBackgroundColor(Color.RED)
            }
        }
    }

    private fun clear() {
        isAnswerRevealed = false
        btnVariants.forEach { it.setBackgroundColor(Color.GRAY) }
    }
}