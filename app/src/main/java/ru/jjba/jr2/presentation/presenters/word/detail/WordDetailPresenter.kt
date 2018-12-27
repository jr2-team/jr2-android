package ru.jjba.jr2.presentation.presenters.word.detail

/*
@InjectViewState
class WordDetailPresenter(
    private val wordId: Long?,
    val wordInteractor: WordInteractor = WordInteractor(),
    val interpretationDbRepository: InterpretationDbRepository = InterpretationDbRepository()
) : MvpPresenter<WordDetailView>() {

    override fun onFirstViewAttach() {
        if (wordId != null) {
            wordInteractor.getWordById(wordId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        viewState.showWord(it)
                        showInterps()
                    }
                )
        }
    }

    private fun showInterps() {
        interpretationDbRepository.getByWordId(wordId!!)
            .first(kotlin.collections.emptyList())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    viewState.showInterps(it)
                }
            )
    }
}
*/
