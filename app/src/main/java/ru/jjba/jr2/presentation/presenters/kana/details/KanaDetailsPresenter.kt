package ru.jjba.jr2.presentation.presenters.kana.details

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import ru.jjba.jr2.domain.interactor.KanaInteractor

@InjectViewState
class KanaDetailsPresenter(
        private val kanaId: String,
        private val kanaInteractor: KanaInteractor = KanaInteractor()
): MvpPresenter<KanaDetailsView>() {
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
}