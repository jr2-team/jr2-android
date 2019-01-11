package ru.jjba.jr2.presentation.viewmodel.vocab.word

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import ru.jjba.jr2.presentation.ui.vocab.word.group.WordGroupFragmentDirections
import ru.jjba.jr2.presentation.viewmodel.BaseViewModel
import ru.jjba.jr2.presentation.viewmodel.ViewModelEvent

class WordGroupViewModel : BaseViewModel() {
    private lateinit var wordGroups: MutableLiveData<List<String>>

    private val navToWordListEvent = MutableLiveData<ViewModelEvent<NavDirections>>()

    fun observeWordGroups(): LiveData<List<String>> {
        if (!::wordGroups.isInitialized) {
            wordGroups = MutableLiveData()

            wordGroups.value = listOf(
                    "Пользовательские группы",
                    "JLPT 5", "JLPT 4", "JLPT 3", "JLPT 2", "JLPT 1"
            )
        }
        return wordGroups
    }

    fun observeNavToWordListEvent(): LiveData<ViewModelEvent<NavDirections>> = navToWordListEvent

    fun onWordGroupClick(wordGroup: String/*WordGroup*/) {
        val direction = WordGroupFragmentDirections.actionWordGroupToWordList().apply {
            wordGroupId = 0//wordGroup.id
        }
        navToWordListEvent.value = ViewModelEvent(direction)
    }
}