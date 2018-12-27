package ru.jjba.jr2.presentation.presenters.kana.list

/*
@InjectViewState
class KanaPresenter(
        private val kanaInteractor: KanaInteractor = KanaInteractor()
) : MvpPresenter<KanaView>() {
    private var kanaTask: Disposable = Disposables.disposed()

    private var kanaList: List<Kana> = emptyList()

    override fun onDestroy() {
        kanaTask.dispose()
    }

    fun getKana(){
        viewState.setKanaList(kanaList)
    }

    fun fillList(nigoriMode: Boolean) {
        kanaTask = if (nigoriMode) {
            kanaInteractor.getOnlyAdditionalSound()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onNext = {
                                kanaList = it
                                getKana()
                            }
                    )
        } else {
            kanaInteractor.getOnlyKana()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onNext = {
                                kanaList = it
                                getKana()
                            }
                    )
        }
    }
}*/
