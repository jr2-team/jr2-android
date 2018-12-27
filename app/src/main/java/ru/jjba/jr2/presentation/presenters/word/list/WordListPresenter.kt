package ru.jjba.jr2.presentation.presenters.word.list

/*
@InjectViewState
class WordListPresenter(
        private val wordInteractor: WordInteractor = WordInteractor()
) : MvpPresenter<WordListView>() {

    private var wordsTask: Disposable = Disposables.disposed()

    override fun onFirstViewAttach() {
        wordsTask = wordInteractor.getAllWords()
                .first(emptyList())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            viewState.showWordList(it)
                        }
                )
    }

    override fun onDestroy() {
        wordsTask.dispose()
    }
}*/
