package ru.jjba.jr2.presentation.viewmodel.vocab.word

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import ru.jjba.jr2.App
import ru.jjba.jr2.domain.entity.Word

class WordListViewModel(
        private val app: App = App.instance
) : ViewModel() {
    private lateinit var words: MutableLiveData<List<Word>>

    fun getWords(): LiveData<List<Word>> {
        if (!::words.isInitialized) {
            words = MutableLiveData()
            // TODO: Refactor
            val moshi = Moshi.Builder().build()
            val wordsAdapter: JsonAdapter<List<Word>> = moshi.adapter(
                    Types.newParameterizedType(List::class.java, Word::class.java)
            )
            val testWords = wordsAdapter.fromJson(app.getAssetContent("word.json"))
            words.value = testWords
        }
        return words
    }
}