package io.github.jr2team.jr2android.presentation.viewmodel.vocab.word

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import io.github.jr2team.jr2android.data.repository.WordDbRepository
import io.github.jr2team.jr2android.domain.entity.Word
import io.github.jr2team.jr2android.presentation.viewmodel.BaseViewModel

class WordDetailViewModel(
        private val wordRepository: WordDbRepository = WordDbRepository()
) : BaseViewModel() {
    private var wordIdArg = 0

    private val word = MutableLiveData<Word>()

    fun setArgs(wordId: Int) {
        wordIdArg = wordId
        fetchData()
    }

    fun observeWord(): LiveData<Word> = word

    private fun fetchData() = launch {
        word.postValue(wordRepository.getById(wordIdArg).await())
    }
}