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
import ru.jjba.jr2.presentation.ui.util.NavDetail
import ru.jjba.jr2.presentation.ui.vocab.word.detail.WordDetailFragmentDirections
import ru.jjba.jr2.presentation.viewmodel.BaseViewModel
import ru.jjba.jr2.presentation.viewmodel.ViewModelEvent
import kotlin.random.Random

class WordDetailViewModel(
        private val detailNavigator: DetailNavigator = App.instance.detailNavigator,
        private val wordRepository: WordDbRepository = WordDbRepository()
) : BaseViewModel() {
    private var wordIdArg = 0

    private val word = MutableLiveData<Word>()
    private val navToWordDetailEvent = MutableLiveData<ViewModelEvent<NavDirections>>()
    private val navToKanjiDetailEvent = MutableLiveData<ViewModelEvent<NavDirections>>()

    private val navTitle = detailNavigator.observeNavTitle()
    private val navDetails = detailNavigator.observeNavDetails()
    private val navDetailsStateEvent = MutableLiveData<ViewModelEvent<String>>()

    fun setArgs(wordId: Int) {
        wordIdArg = wordId
        fetchData()
    }

    fun observeWord(): LiveData<Word> = word

    fun observeNavToWordDetailEvent(): LiveData<ViewModelEvent<NavDirections>> =
            navToWordDetailEvent

    fun observeNavToKanjiDetailEvent(): LiveData<ViewModelEvent<NavDirections>> =
            navToKanjiDetailEvent

    fun observeNavTitle(): LiveData<SpannableStringBuilder> = navTitle
    fun observeNavDetails(): LiveData<List<String>> = navDetails
    fun observeNavDetailsStateEvent(): LiveData<ViewModelEvent<String>> = navDetailsStateEvent

    fun onWordClick() {
        val direction = WordDetailFragmentDirections.actionWordDetailFragmentSelf(Random.nextInt(1, 3000))
        navToWordDetailEvent.postValue(ViewModelEvent(direction))
    }

    fun onKanjiClick() {
        val direction = WordDetailFragmentDirections.actionWordDetailFragmentToKanjiDetailFragment()
        navToWordDetailEvent.postValue(ViewModelEvent(direction))
    }

    fun onNavForward() {
        word.value?.let { word ->
            detailNavigator.navigatedForward(NavDetail(word.value, "слово"))
        }
    }

    fun onNavBack(times: Int) {
        detailNavigator.navigatedBack(times)
    }

    fun onSaveNavDetailsState() {
        val state = detailNavigator.saveNavDetailsStateToJson()
        navDetailsStateEvent.postValue(ViewModelEvent(state))
    }

    fun onRestoreNavDetailsState(state: String) {
        detailNavigator.restoreNavDetailFromJson(state)
    }

    // TODO: Убрать
    fun clear() {
        onCleared()
    }

    private fun fetchData() = launch {
        word.postValue(wordRepository.getById(wordIdArg).await())
    }
}