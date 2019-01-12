package ru.jjba.jr2.presentation.viewmodel.vocab.word

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.jjba.jr2.App
import ru.jjba.jr2.data.repository.WordDbRepository
import ru.jjba.jr2.domain.entity.Word
import ru.jjba.jr2.presentation.ui.vocab.word.list.WordListFragmentDirections
import ru.jjba.jr2.presentation.viewmodel.BaseViewModel
import ru.jjba.jr2.presentation.viewmodel.ViewModelEvent
import java.util.concurrent.TimeUnit

class WordListViewModel(
        private val app: App = App.instance
) : BaseViewModel() {
    private lateinit var words: LiveData<List<Word>>

    private val navToWordDetailEvent = MutableLiveData<ViewModelEvent<NavDirections>>()
    private val wordsIsLoading = MutableLiveData<Boolean>()

    fun setArgs(wordListId: Int) {

    }

    fun observeWords(): LiveData<List<Word>> {
        if (!::words.isInitialized) {
            // TODO: Удалить

            val wordsAdapter: JsonAdapter<List<Word>> =  Moshi.Builder().build().adapter(
                    Types.newParameterizedType(List::class.java, Word::class.java)
            )
            val testWords = wordsAdapter.fromJson(app.getAssetContent("word.json"))
            WordDbRepository().dropAndInsert(testWords!!)
                    .delay(10, TimeUnit.SECONDS)
                    .doOnSubscribe {wordsIsLoading.postValue(true)}
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(onComplete = { wordsIsLoading.postValue(false) })
                    .addTo(compDisp)

            val repository = WordDbRepository()
            words = repository.getAll()
        }
        return words
    }

    fun observeNavToWordDetailEvent(): LiveData<ViewModelEvent<NavDirections>> =
            navToWordDetailEvent

    fun observeWordsIsLoading(): LiveData<Boolean> = wordsIsLoading

    fun onWordClick(word: Word) {
        val direction = WordListFragmentDirections.actionWordListToWordDetail().apply {
            wordId = word.id
        }
        navToWordDetailEvent.value = ViewModelEvent(direction)
    }

}