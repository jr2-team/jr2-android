package ru.jjba.jr2.presentation.presenters.kana

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import ru.jjba.jr2.domain.interactor.KanaInteractor
import ru.jjba.jr2.presentation.ui.kana.KanaAdapter

@InjectViewState
class KanaPresenter(
        private val kanaInteractor: KanaInteractor = KanaInteractor()
) : MvpPresenter<KanaView>() {

    private var kanaAdapter = KanaAdapter(false, false)

    private var englishMode = false
    private var katakanaMode = false

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        fillAdapter()
    }

    private fun fillAdapter() {
        //kanaAdapter.kanaList = kanaInteractor.getAllKana()
        kanaInteractor.getAllKana()
                .first(emptyList())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            kanaAdapter.kanaList = it
                        }
                )
    }

    fun setAdapterMode(englishMode: Boolean, katakanaMode: Boolean) {
        this.englishMode = englishMode
        this.katakanaMode = katakanaMode
        kanaAdapter = KanaAdapter(englishMode, katakanaMode)
        fillAdapter()
    }

    fun getAdapter(): KanaAdapter = kanaAdapter

    fun setSettingsDialog() {
        viewState.createSettingsDialog(englishMode, katakanaMode)
    }
}