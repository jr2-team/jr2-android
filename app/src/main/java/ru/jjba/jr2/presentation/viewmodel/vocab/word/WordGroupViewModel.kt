package ru.jjba.jr2.presentation.viewmodel.vocab.word

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import kotlinx.coroutines.launch
import ru.jjba.jr2.data.repository.GroupDbRepository
import ru.jjba.jr2.data.repository.SectionDbRepository
import ru.jjba.jr2.domain.entity.Group
import ru.jjba.jr2.domain.select.SectionWithGroups
import ru.jjba.jr2.presentation.ui.vocab.word.group.WordGroupFragmentDirections
import ru.jjba.jr2.presentation.viewmodel.BaseViewModel
import ru.jjba.jr2.presentation.viewmodel.ViewModelEvent

class WordGroupViewModel(
        private val sectionRepository: SectionDbRepository = SectionDbRepository(),
        private val groupRepository: GroupDbRepository = GroupDbRepository()
) : BaseViewModel() {
    private val navToWordListEvent = MutableLiveData<ViewModelEvent<NavDirections>>()
    private var wordGroupSections = MutableLiveData<List<SectionWithGroups>>()
    private val areSectionsLoading = MutableLiveData<Boolean>().apply { value = false }

    fun fetchData() = launch {
        areSectionsLoading.postValue(true)
        // TODO : Сделать из этого что-нибудть более адекватное
        val sections = sectionRepository.getSectionsWithGroups().await().apply {
            forEach { s ->
                s.groups.forEach { g ->
                    g.itemsCount = groupRepository
                            .getItemsCountInGroup(g.id)
                            .await()
                }
            }
        }
        wordGroupSections.postValue(sections)
        areSectionsLoading.postValue(false)
    }

    fun clearData() {
        wordGroupSections.value = emptyList()
    }

    fun observeNavToWordListEvent(): LiveData<ViewModelEvent<NavDirections>> = navToWordListEvent
    fun observeWordGroupSections(): LiveData<List<SectionWithGroups>> = wordGroupSections
    fun observeAreSectionsLoading(): LiveData<Boolean> = areSectionsLoading

    fun onWordGroupClick(wordGroup: Group) {
        val direction = WordGroupFragmentDirections
                .actionWordGroupToWordList(wordGroupId = wordGroup.id)
        navToWordListEvent.value = ViewModelEvent(direction)
    }
}