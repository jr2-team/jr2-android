package ru.jjba.jr2.presentation.presenters.kana

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.rxkotlin.subscribeBy
import ru.jjba.jr2.domain.entity.Kana
import ru.jjba.jr2.domain.interactor.KanaInteractor
import java.util.*

@InjectViewState
class KanaPresenter(
        private val kanaInteractor: KanaInteractor = KanaInteractor()
) : MvpPresenter<KanaView>() {
    private var kanaTask: Disposable = Disposables.disposed()

    var kanaList = emptyList<Kana>()

    override fun onDestroy() {
        kanaTask.dispose()
    }

    fun getKana(nigoriMode: Boolean){
        if (!nigoriMode) {
            viewState.setKanaList(kanaList.subList(0, 50)+kanaList.subList(75, 110))
        } else {
            viewState.setKanaList(kanaList.subList(50, 75)+kanaList.subList(110, kanaList.size))
        }
    }

    fun fillList() {
        kanaTask = kanaInteractor.getAllKana()
                .first(emptyList())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            kanaList = it
                            getKana(false)
                        }
                )
    }
}