package ru.jjba.jr2.presentation.viewmodel.vocab.word

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.jjba.jr2.data.repository.WordDbRepository
import ru.jjba.jr2.domain.entity.Word
import ru.jjba.jr2.presentation.viewmodel.BaseViewModel
import kotlin.properties.Delegates

class WordDetailViewModel(
        private val wordRepository: WordDbRepository = WordDbRepository()
) : BaseViewModel() {
    private val word = MutableLiveData<Word>()

    private var wordId: Int by Delegates.observable(0) { _, oldValue, newValue ->
        if (oldValue != newValue) loadWord()
    }

    fun setArgs(wordId: Int) {
        this.wordId = wordId
    }

    fun observeWord(): LiveData<Word> = word

    private fun loadWord() = wordRepository.getById(wordId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                    onSuccess = { word.postValue(it) }
            )
            .addTo(compDisp)
}