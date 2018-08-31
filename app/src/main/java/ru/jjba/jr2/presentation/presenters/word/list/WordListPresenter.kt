package ru.jjba.jr2.presentation.presenters.word.list

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import ru.jjba.jr2.data.repository.InterpretationDbRepository
import ru.jjba.jr2.data.repository.WordDbRepository
import ru.jjba.jr2.domain.entity.Interpretation
import ru.jjba.jr2.domain.entity.Word
import ru.jjba.jr2.presentation.ui.word.list.WordAdapter

@InjectViewState
class WordListPresenter(
        private val wordDbRepository: WordDbRepository = WordDbRepository(),
        private val interpretationDbRepository: InterpretationDbRepository = InterpretationDbRepository()
) : MvpPresenter<WordListView>() {

    private val wordAdapter = WordAdapter()

    /*fun setContent(){
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
    }*/

    fun getAdapter(): WordAdapter = wordAdapter

    private fun insertInterpretation(interpretation: Interpretation) {
        interpretationDbRepository.insert(interpretation).subscribeBy { }
    }

    private fun insertWords(words: List<Word>) {
        wordDbRepository.insert(listOf(
                Word("1", "家", "いえ", 5),
                Word("2", "今日は", "こんにち", 5)
        )).subscribeBy { }
    }

    fun fillAdapter() {
        wordDbRepository.getAll()
                .first(emptyList())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            viewState.setWordAdapter(it)
                        }
                )
    }
}