package ru.jjba.jr2.presentation.presenters.kana.list

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.rxkotlin.subscribeBy
import ru.jjba.jr2.domain.entity.Kana
import ru.jjba.jr2.domain.interactor.KanaInteractor

@InjectViewState
class KanaPresenter(
        private val kanaInteractor: KanaInteractor = KanaInteractor()
) : MvpPresenter<KanaView>() {
    private var kanaTask: Disposable = Disposables.disposed()

    private var kanaList: List<Kana> = emptyList()

    override fun onDestroy() {
        kanaTask.dispose()
    }

    fun getKana() {
        viewState.setKanaList(kanaList)
    }

    fun fillList(nigoriMode: Boolean) {
        kanaTask = kanaInteractor.getSpecificKana(nigoriMode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            kanaList = it
                            getKana()
                        }
                )
    }
}