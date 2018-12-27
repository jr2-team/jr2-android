package ru.jjba.jr2.presentation.presenters.kana.detail

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import ru.jjba.jr2.domain.interactor.KanaInteractor

/*
@InjectViewState
class KanaDetailPresenter(
        private val kanaId: String,
        private val kanaInteractor: KanaInteractor = KanaInteractor()
) : MvpPresenter<KanaDetailView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        kanaInteractor.getKana(kanaId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            viewState.showKana(it)
                        }
                )
    }
}*/
