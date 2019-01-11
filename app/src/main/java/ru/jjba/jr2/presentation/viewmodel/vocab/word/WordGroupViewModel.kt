package ru.jjba.jr2.presentation.viewmodel.vocab.word

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import ru.jjba.jr2.presentation.ui.vocab.word.group.WordGroupFragmentDirections
import ru.jjba.jr2.presentation.viewmodel.ViewModelEvent

class WordGroupViewModel : ViewModel() {
    private lateinit var wordGroups: MutableLiveData<List<String>>

    private val navigateToWordList = MutableLiveData<ViewModelEvent<NavDirections>>()

    fun getWordGroups(): LiveData<List<String>> {
        if (!::wordGroups.isInitialized) {
            wordGroups = MutableLiveData()

            wordGroups.value = listOf(
                    "Пользовательские группы",
                    "JLPT 5", "JLPT 4", "JLPT 3", "JLPT 2", "JLPT 1"
            )
        }
        return wordGroups
    }

    fun getNavigationToWordList(): LiveData<ViewModelEvent<NavDirections>> = navigateToWordList

    fun onWordGroupClick(wordGroup: String/*WordGroup*/) {
        val direction = WordGroupFragmentDirections.actionWordGroupToWordList().apply {
            wordGroupId = 0//wordGroup.id
        }
        navigateToWordList.value = ViewModelEvent(direction)
    }
}