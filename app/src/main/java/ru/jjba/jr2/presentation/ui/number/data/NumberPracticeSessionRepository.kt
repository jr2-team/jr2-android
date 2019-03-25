package ru.jjba.jr2.presentation.ui.number.data

import ru.jjba.jr2.presentation.ui.number.domani.JpNumber
import ru.jjba.jr2.presentation.ui.number.domani.JpNumberQuiz
import ru.jjba.jr2.presentation.ui.number.domani.JpNumberQuizType
import kotlin.random.Random

// TODO: Generation setting in primary constructor
class NumberPracticeSessionRepository {
    private val quizzesPool: MutableList<JpNumberQuiz>
    private var quizzesPoolIdx: Int

    init {
        quizzesPool = generateNewPool().take(10).toMutableList()
        quizzesPoolIdx = -1
    }

    fun getNextQuiz(): JpNumberQuiz? {
        quizzesPoolIdx += 1
        return quizzesPool.getOrNull(quizzesPoolIdx)
    }

    fun resetSessionPool() {
        quizzesPoolIdx = -1
        quizzesPool.shuffle()
    }

    private fun generateNewPool() = generateSequence {
        val jpNumber = JpNumber(Random.nextInt(1, 1000))
        val quizType = JpNumberQuizType.getRandom(0, 1, 2, 3, 4, 5)
        JpNumberQuiz(jpNumber, quizType)
    }

    private fun restoreSession() {
        // TODO: Implement
    }

    companion object {
        // TODO: serialize, deserialize active session
        fun restoreSession() = NumberPracticeSessionRepository().apply {
            restoreSession()
        }
    }
}