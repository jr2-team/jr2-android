package ru.jjba.jr2.presentation.viewmodel.vocab.word

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import ru.jjba.jr2.data.repository.GroupDbRepository
import ru.jjba.jr2.domain.entity.GroupSection
import ru.jjba.jr2.presentation.ui.vocab.word.group.WordGroupAdapterCollapsed.Section
import ru.jjba.jr2.presentation.ui.vocab.word.group.WordGroupFragmentDirections
import ru.jjba.jr2.presentation.viewmodel.BaseViewModel
import ru.jjba.jr2.presentation.viewmodel.ViewModelEvent

class WordGroupViewModel(
        private val groupRepository: GroupDbRepository = GroupDbRepository()
) : BaseViewModel() {
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

    val groups = groupRepository.getAllWordGroup()

    val bar = MutableLiveData<List<Section>>()

    fun loadSections() {
        val sections = listOf(
                Section("Пользовательские группы", mutableListOf()),
                Section("JLPT 1", mutableListOf()),
                Section("JLPT 2", mutableListOf()),
                Section("JLPT 3", mutableListOf()),
                Section("JLPT 4", mutableListOf()),
                Section("JLPT 5", mutableListOf())
        )

        groups.value?.forEach { group ->
            when (group.groupSection) {
                GroupSection.CUSTOM -> sections[0].items.add(group.name)
                GroupSection.JLPT_1 -> sections[1].items.add(group.name)
                GroupSection.JLPT_2 -> sections[2].items.add(group.name)
                GroupSection.JLPT_3 -> sections[3].items.add(group.name)
                GroupSection.JLPT_4 -> sections[4].items.add(group.name)
                GroupSection.JLPT_5 -> sections[5].items.add(group.name)
            }
        }
        bar.value = sections
    }

    fun observeGroupsOfKanjis() = groupRepository.getAllKanjiGroup()

    fun onWordGroupClick(wordGroup: String/*GroupOfWordJoin*/) {
        val direction = WordGroupFragmentDirections.actionWordGroupToWordList().apply {
            wordGroupId = 0//wordGroup.id
        }
        navToWordListEvent.value = ViewModelEvent(direction)
    }
}