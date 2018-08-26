package ru.jjba.jr2.presentation.presenters.kana

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.jjba.jr2.domain.interactor.KanaInteractor
import ru.jjba.jr2.presentation.ui.kana.KanaAdapter

@InjectViewState
class KanaPresenter(
        private val kanaInteractor: KanaInteractor = KanaInteractor()
) : MvpPresenter<KanaView>() {

    private val kanaAdapter = KanaAdapter()

    fun getAdapter(): KanaAdapter = kanaAdapter

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        fillAdapter()
    }

    private fun fillAdapter() {
        kanaAdapter.kanaList = kanaInteractor.getAllKana()
    }
}