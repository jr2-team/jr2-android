package ru.jjba.jr2.presentation.viewmodel.vocab.word

import android.text.SpannableStringBuilder
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import ru.jjba.jr2.App
import ru.jjba.jr2.data.repository.WordDbRepository
import ru.jjba.jr2.domain.entity.Word
import ru.jjba.jr2.presentation.ui.util.DetailNavigator
import ru.jjba.jr2.presentation.viewmodel.BaseViewModel
import kotlin.properties.Delegates.observable

class WordDetailViewModel(
        val detailNavigator: DetailNavigator = App.instance.detailNavigator,
        private val wordRepository: WordDbRepository = WordDbRepository()
) : BaseViewModel() {
    private var wordIdArg = 0

    private val word = MutableLiveData<Word?>()
    private val navTitle = detailNavigator.observeNavTitle()

    fun setArgs(wordId: Int) {
        wordIdArg = wordId
        clearData()
        fetchData()
    }

    fun observeWord(): LiveData<Word?> = word
    fun observeNavTitle(): LiveData<SpannableStringBuilder> = navTitle

    private fun fetchData() = launch {
        word.postValue(wordRepository.getById(wordIdArg).await())
    }

    // TODO: Использовать MediatorLiveData, взамен этому костылю?
    fun clearData() {
        word.postValue(null)
    }
}