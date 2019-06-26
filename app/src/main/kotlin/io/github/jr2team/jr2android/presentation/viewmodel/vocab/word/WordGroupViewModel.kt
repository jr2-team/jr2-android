package io.github.jr2team.jr2android.presentation.viewmodel.vocab.word

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import io.github.jr2team.jr2android.domain.entity.Group
import io.github.jr2team.jr2android.domain.room.select.SectionWithGroups
import io.github.jr2team.jr2android.domain.usecase.SectionUseCase
import io.github.jr2team.jr2android.presentation.ui.vocab.word.group.WordGroupFragmentDirections
import io.github.jr2team.jr2android.presentation.viewmodel.ViewModelEvent
import io.github.jr2team.jr2android.presentation.viewmodel.util.defaultValue
import kotlinx.coroutines.launch

class WordGroupViewModel(
        private val sectionUseCase: SectionUseCase = SectionUseCase()
) : ViewModel() {
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

    private fun fetchData() = viewModelScope.launch {
        areSectionsLoading.postValue(true)
        wordGroupSections.postValue(sectionUseCase.getWordSections())

    }.invokeOnCompletion { throwable ->
        if (throwable == null) {
            areSectionsLoading.postValue(false)
        }
    }
}