package ru.jjba.jr2.presentation.viewmodel.vocab.word

import android.text.SpannableStringBuilder
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import kotlinx.coroutines.launch
import ru.jjba.jr2.App
import ru.jjba.jr2.data.repository.WordDbRepository
import ru.jjba.jr2.domain.entity.Word
import ru.jjba.jr2.presentation.ui.util.DetailNavigator
import ru.jjba.jr2.presentation.ui.vocab.word.detail.WordDetailFragmentDirections
import ru.jjba.jr2.presentation.viewmodel.BaseViewModel
import ru.jjba.jr2.presentation.viewmodel.ViewModelEvent
import kotlin.random.Random

class WordDetailViewModel(
        val detailNavigator: DetailNavigator = App.instance.detailNavigator,
        private val wordRepository: WordDbRepository = WordDbRepository()
) : BaseViewModel() {
    private var wordIdArg = 0

    private val word = MutableLiveData<Word?>()
    private val navTitle = detailNavigator.observeNavTitle()
    private val navToWordDetailEvent = MutableLiveData<ViewModelEvent<NavDirections>>()
    private val navToKanjiDetailEvent = MutableLiveData<ViewModelEvent<NavDirections>>()

    fun setArgs(wordId: Int) {
        wordIdArg = wordId
        // TODO: временная загулшка
        clearData()
        fetchData()
    }

    fun observeWord(): LiveData<Word?> = word
    fun observeNavTitle(): LiveData<SpannableStringBuilder> = navTitle
    // TODO: Возможно стоит создать класс NavigationEvent : LiveData ?
    fun observeNavToWordDetailEvent(): LiveData<ViewModelEvent<NavDirections>> =
            navToWordDetailEvent
    fun observeNavToKanjiDetailEvent(): LiveData<ViewModelEvent<NavDirections>> =
            navToKanjiDetailEvent

    // TODO: Использовать MediatorLiveData вместо этого уродства?
    fun clearData() {
        word.postValue(null)
    }

    fun onWordClick() {
        val direction = WordDetailFragmentDirections.actionWordDetailFragmentSelf(Random.nextInt(1, 3000))
        navToWordDetailEvent.postValue(ViewModelEvent(direction))
    }

    fun onKanjiClick() {
        val direction = WordDetailFragmentDirections.actionWordDetailFragmentToKanjiDetailFragment()
        navToWordDetailEvent.postValue(ViewModelEvent(direction))
    }

    private fun fetchData() = launch {
        word.postValue(wordRepository.getById(wordIdArg).await())
    }
}