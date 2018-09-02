package ru.jjba.jr2.presentation.presenters.kana

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.jjba.jr2.domain.interactor.KanaInteractor
import ru.jjba.jr2.presentation.ui.kana.KanaAdapter

@InjectViewState
class KanaPresenter(
        private val kanaInteractor: KanaInteractor = KanaInteractor()
) : MvpPresenter<KanaView>() {

    private var kanaAdapter = KanaAdapter()

    var englishMode = false
    var katakanaMode = false

    fun fillAdapter() {
        kanaInteractor.getAllKana()
                .first(emptyList())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            kanaAdapter.kanaList = it
                        }
                )
    }

    fun setAdapterMode() {
        kanaAdapter.englishMode = englishMode
        kanaAdapter.katakanaMode = katakanaMode
    }

    fun getAdapter(): KanaAdapter = kanaAdapter
}