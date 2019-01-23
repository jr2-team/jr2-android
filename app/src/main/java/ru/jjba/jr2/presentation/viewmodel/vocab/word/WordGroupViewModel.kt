package ru.jjba.jr2.presentation.viewmodel.vocab.word

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import kotlinx.coroutines.*
import ru.jjba.jr2.data.repository.SectionDbRepository
import ru.jjba.jr2.domain.entity.Group
import ru.jjba.jr2.domain.select.SectionWithGroups
import ru.jjba.jr2.presentation.ui.vocab.word.group.WordGroupFragmentDirections
import ru.jjba.jr2.presentation.viewmodel.BaseViewModel
import ru.jjba.jr2.presentation.viewmodel.ViewModelEvent

class WordGroupViewModel(
        private val sectionRepository: SectionDbRepository = SectionDbRepository()
) : BaseViewModel() {
    private val navToWordListEvent = MutableLiveData<ViewModelEvent<NavDirections>>()
    private var wordGroupSections = MutableLiveData<List<SectionWithGroups>>()

    fun observeWordGroupSections(): LiveData<List<SectionWithGroups>> {
        launch {
            withContext(Dispatchers.Default) {
                sectionRepository.getSectionsWithGroups()
            }.also {
                wordGroupSections.postValue(it)
            }
        }
        return wordGroupSections
    }

    fun observeNavToWordListEvent(): LiveData<ViewModelEvent<NavDirections>> = navToWordListEvent

    fun onWordGroupClick(wordGroup: Group) {
        val direction = WordGroupFragmentDirections.actionWordGroupToWordList().apply {
            wordGroupId = wordGroup.id
        }
        navToWordListEvent.value = ViewModelEvent(direction)
    }
}