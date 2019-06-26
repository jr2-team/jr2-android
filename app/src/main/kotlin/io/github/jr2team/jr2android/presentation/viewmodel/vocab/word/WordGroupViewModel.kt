package io.github.jr2team.jr2android.presentation.viewmodel.vocab.word

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import kotlinx.coroutines.launch
import io.github.jr2team.jr2android.domain.entity.Group
import io.github.jr2team.jr2android.domain.room.select.SectionWithGroups
import io.github.jr2team.jr2android.domain.usecase.SectionUseCase
import io.github.jr2team.jr2android.presentation.ui.vocab.word.group.WordGroupFragmentDirections
import io.github.jr2team.jr2android.presentation.viewmodel.BaseViewModel
import io.github.jr2team.jr2android.presentation.viewmodel.ViewModelEvent
import io.github.jr2team.jr2android.presentation.viewmodel.util.defaultValue

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