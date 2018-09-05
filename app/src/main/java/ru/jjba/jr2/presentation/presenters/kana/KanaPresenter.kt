package ru.jjba.jr2.presentation.presenters.kana

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.jjba.jr2.domain.interactor.KanaInteractor
import ru.jjba.jr2.presentation.ui.kana.KanaAdapter

@InjectViewState
class KanaPresenter(
        private val kanaInteractor: KanaInteractor = KanaInteractor()
) : MvpPresenter<KanaView>() {
    private var kanaTask: Disposable = Disposables.disposed()

    override fun onFirstViewAttach() {
        kanaTask = kanaInteractor.getAllKana()
                .first(emptyList())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            viewState.setKanaList(it)
                        }
                )
    }

    override fun onDestroy() {
        kanaTask.dispose()
    }
}