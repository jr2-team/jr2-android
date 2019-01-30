package ru.jjba.jr2.presentation.viewmodel.vocab.word

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import ru.jjba.jr2.App
import ru.jjba.jr2.data.repository.WordDbRepository
import ru.jjba.jr2.domain.entity.Word
import ru.jjba.jr2.presentation.ui.util.DetailNavigator
import ru.jjba.jr2.presentation.viewmodel.BaseViewModel

class WordDetailViewModel(
        val detailNavigator: DetailNavigator = App.instance.detailNavigator,
        private val wordRepository: WordDbRepository = WordDbRepository()
) : BaseViewModel() {
    private var wordIdArg = 0

    private val word = MutableLiveData<Word?>()
    /*private val navTitle = detailNavigator.getNavTitleLive()
    private val navDetails = detailNavigator.observeNavDetails()
    private val navDetailsStateEvent = MutableLiveData<ViewModelEvent<String>>()*/

    init {
        word.value = null
    }

    fun setArgs(wordId: Int) {
        wordIdArg = wordId
        fetchData()
    }

    fun observeWord(): LiveData<Word?> = word

    private fun fetchData() = launch {
        word.postValue(wordRepository.getById(wordIdArg).await())
    }
    /*fun observeNavTitle(): LiveData<SpannableStringBuilder?> = navTitle
    fun observeNavDetails(): LiveData<List<String>> = navDetails
    fun observeNavDetailsStateEvent(): LiveData<ViewModelEvent<String>> = navDetailsStateEvent

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
    }*/
}