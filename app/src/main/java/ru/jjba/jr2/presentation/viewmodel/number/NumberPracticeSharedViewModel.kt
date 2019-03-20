package ru.jjba.jr2.presentation.viewmodel.number

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.jjba.jr2.presentation.viewmodel.BaseViewModel
import ru.jjba.jr2.presentation.viewmodel.util.LiveData.CounterLiveData
import kotlin.random.Random

class NumberPracticeSharedViewModel : BaseViewModel() {
    private val sessionTimer = CounterLiveData(coroutineContext)
    private val nextQuiz = MutableLiveData<NumberPracticeQuiz>()

    private val quizzesPool = mutableListOf<NumberPracticeQuiz>()
    private var currentQuizIdx = -1

    fun observeSessionTime(): LiveData<Int> = sessionTimer
    fun observeNextQuiz(): LiveData<NumberPracticeQuiz> = nextQuiz

    fun onStartPracticeSession() {
        // TODO: if not requested to repeat then create new pool else shuffle
        // launch coroutin on pool generation, on finish emmit event "navigateToQuizFragment"
        createSessionQuizzesPool()
    }

    fun onFinishPracticeSession() {
        // TODO: emmit event of navigation to result fragment or pop back if total answers less then 1
    }

    fun pollNextQuiz() {
        if (currentQuizIdx < quizzesPool.count()) {
            currentQuizIdx += 1
        } else {
            onFinishPracticeSession()
        }
        nextQuiz.postValue(quizzesPool[currentQuizIdx])
    }

    fun onTimerStateChange(isPauseRequested: Boolean) {
        sessionTimer.changeCounterState(!isPauseRequested)
    }

    private fun createSessionQuizzesPool() {
        // TODO: Create the questions pool by settings from shared prefs
        val genFromValue = 1
        val genToValue = 1000
        val quizCount = 10

        for (i in 0..quizCount) {
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

            quizzesPool.add(NumberPracticeQuiz(newCorrectVariant, newVariants, ""))
        }
    }
}
// TODO: Come up with the better idea what properties a quiz should have
class NumberPracticeQuiz(
        val question: Int,
        val variants: List<Int>,
        val type: String
)