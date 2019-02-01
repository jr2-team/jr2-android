package ru.jjba.jr2.presentation.viewmodel.vocab.word

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import kotlinx.coroutines.launch
import ru.jjba.jr2.domain.entity.Group
import ru.jjba.jr2.domain.select.SectionWithGroups
import ru.jjba.jr2.domain.usecase.SectionUseCase
import ru.jjba.jr2.presentation.ui.vocab.word.group.WordGroupFragmentDirections
import ru.jjba.jr2.presentation.viewmodel.BaseViewModel
import ru.jjba.jr2.presentation.viewmodel.ViewModelEvent
import ru.jjba.jr2.presentation.viewmodel.util.defaultValue

class WordGroupViewModel(
        private val sectionUseCase: SectionUseCase = SectionUseCase()
) : BaseViewModel() {
    private val navToWordListEvent = MutableLiveData<ViewModelEvent<NavDirections>>()

    private var wordGroupSections = MutableLiveData<List<SectionWithGroups>>()
    private val areSectionsLoading = MutableLiveData<Boolean>().defaultValue(false)

    init {
        fetchData()
    }

    fun observeNavToWordListEvent(): LiveData<ViewModelEvent<NavDirections>> = navToWordListEvent
    fun observeWordGroupSections(): LiveData<List<SectionWithGroups>> = wordGroupSections
    fun observeAreSectionsLoading(): LiveData<Boolean> = areSectionsLoading

    fun onWordGroupClick(wordGroup: Group) {
        val direction = WordGroupFragmentDirections
                .actionWordGroupToWordList(wordGroupId = wordGroup.id)
        navToWordListEvent.value = ViewModelEvent(direction)
    }

    private fun fetchData() = launch {
        areSectionsLoading.postValue(true)
        wordGroupSections.postValue(sectionUseCase.getWordSections())

    }.invokeOnCompletion { throwable ->
        if (throwable == null) {
            areSectionsLoading.postValue(false)
        }
    }
}