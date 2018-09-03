package ru.jjba.jr2.presentation.presenters.word.list

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.rxkotlin.subscribeBy
import ru.jjba.jr2.data.repository.InterpretationDbRepository
import ru.jjba.jr2.data.repository.WordDbRepository
import ru.jjba.jr2.domain.entity.Interp
import ru.jjba.jr2.domain.entity.Word
import ru.jjba.jr2.domain.interactor.WordInteractor
import ru.jjba.jr2.presentation.ui.word.list.WordAdapter

@InjectViewState
class WordListPresenter(
        private val wordInteractor: WordInteractor = WordInteractor()
) : MvpPresenter<WordListView>() {

    private var wordsTask: Disposable = Disposables.disposed()

    override fun onFirstViewAttach() {
        wordsTask = wordInteractor.getAllWords()
                .first(emptyList())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            viewState.showWordList(it)
                        }
                )
    }

    override fun onDestroy() {
        wordsTask.dispose()
    }
}