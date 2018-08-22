package ru.jjba.jr2.presentation.presenters.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import ru.jjba.jr2.data.repository.interpretation.InterpretationDbRepository
import ru.jjba.jr2.data.repository.word.WordDbRepository
import ru.jjba.jr2.domain.entity.Interpretation
import ru.jjba.jr2.domain.entity.Word

@InjectViewState
class MainActivityPresenter(
        private val wordDbRepository: WordDbRepository = WordDbRepository(),
        private val interpretationDbRepository: InterpretationDbRepository = InterpretationDbRepository()
) : MvpPresenter<MainActivityView>() {

    fun setContent(){
        insertWords(
                listOf(
                        Word("1", "家", "いえ", 5),
                        Word("2", "今日は", "こんにち", 5)
                )
        )
        insertInterpretation(Interpretation("1", "house; residence; dwelling", "noun", "1"))
        insertInterpretation(Interpretation("2", "family; household", "noun", "1"))
        insertInterpretation(Interpretation("3", "hello; good day; good afternoon", "", "2"))
        fillAdapter()
    }

    private fun insertInterpretation(interpretation: Interpretation) {
        interpretationDbRepository.insert(interpretation).subscribeBy {  }
    }

    private fun insertWords(words: List<Word>) {
        wordDbRepository.insert(words).subscribeBy(
                onComplete = {
                    viewState.showMessage("words complete")
                },
                onError = {
                    viewState.showMessage("words error")
                }
        )
    }

    private fun fillAdapter() {
        wordDbRepository.getAll()
                .first(emptyList())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            viewState.setWordAdapter(it)
                            viewState.showMessage("fill complete")
                        },
                        onError = {
                            viewState.showMessage("fill error")
                            it.printStackTrace()
                        }
                )
    }
}