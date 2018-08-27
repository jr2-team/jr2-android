package ru.jjba.jr2.presentation.presenters.word.details

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import ru.jjba.jr2.data.repository.interpretation.InterpretationDbRepository
import ru.jjba.jr2.domain.interactor.WordInteractor

@InjectViewState
class WordDetailsPresenter(
        private val wordId: String?,
        val wordInteractor: WordInteractor = WordInteractor()
) : MvpPresenter<WordDetailsView>() {

    override fun onFirstViewAttach() {
        if (wordId != null) {
            wordInteractor.getWordById(wordId)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onSuccess = {
                                viewState.showWord(it)
                            }
                    )
        }
    }

}