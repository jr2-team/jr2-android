package ru.jjba.jr2.presentation.viewmodel.vocab.word

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WordGroupViewModel : ViewModel() {
    private lateinit var wordGroups: MutableLiveData<List<String>>

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
}