package ru.jjba.jr2.presentation.viewmodel.number

import ru.jjba.jr2.presentation.ui.number.PracticeNumberQuizType
import ru.jjba.jr2.presentation.ui.number.PracticeNumberSingleQuiz
import ru.jjba.jr2.presentation.util.JpNumber
import kotlin.random.Random

class NumberPracticeSessionRepository(/*generation settings*/) {
    private var quizzesPool: List<PracticeNumberSingleQuiz> = emptyList()

    fun generateNewPool() {
        val quizGenerator = generateSequence {
            val jpNumber = JpNumber(Random.nextInt(1, 1000))
            val quizType = PracticeNumberQuizType.getRandom(0, 1, 2, 3, 4, 5)
            PracticeNumberSingleQuiz(jpNumber, quizType)
        }
        quizzesPool = quizGenerator.take(10).toList()
    }
}