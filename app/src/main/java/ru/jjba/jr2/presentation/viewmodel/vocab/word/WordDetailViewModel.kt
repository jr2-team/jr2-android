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
    private val word = MutableLiveData<Word>()
    private var wordIdArg: Int by observable(Int.MIN_VALUE) { _, oldValue, newValue ->
        if (oldValue != newValue) {
            //word.postValue(wordRepository.getById(newValue))
        }
    }

    val navTitle: LiveData<SpannableStringBuilder> = detailNavigator.observeNavTitle()

    fun setArgs(wordId: Int) {
        wordIdArg = wordId
    }

    fun fetchData() = launch {
        word.postValue(wordRepository.getById(wordIdArg).await())
    }

    fun observeWord(): LiveData<Word> = word

    // Костыль
    fun onContentCleared() = onCleared()
}