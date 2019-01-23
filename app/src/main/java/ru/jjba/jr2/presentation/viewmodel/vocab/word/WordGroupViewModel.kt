package ru.jjba.jr2.presentation.viewmodel.vocab.word

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import ru.jjba.jr2.data.repository.GroupDbRepository
import ru.jjba.jr2.domain.entity.Group
import ru.jjba.jr2.domain.entity.GroupSection
import ru.jjba.jr2.presentation.ui.vocab.word.group.WordGroupFragmentDirections
import ru.jjba.jr2.presentation.ui.vocab.word.group.WordGroupSection
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

    val bar = MutableLiveData<List<WordGroupSection>>()

    fun loadSections() {
        val sections = listOf(
                WordGroupSection("Пользовательские группы", mutableListOf()),
                WordGroupSection("JLPT 1", mutableListOf()),
                WordGroupSection("JLPT 2", mutableListOf()),
                WordGroupSection("JLPT 3", mutableListOf()),
                WordGroupSection("JLPT 4", mutableListOf()),
                WordGroupSection("JLPT 5", mutableListOf())
        )

        groups.value?.forEach { group ->
            when (group.groupSection) {
                GroupSection.CUSTOM -> sections[0].groups.add(group)
                GroupSection.JLPT_1 -> sections[1].groups.add(group)
                GroupSection.JLPT_2 -> sections[2].groups.add(group)
                GroupSection.JLPT_3 -> sections[3].groups.add(group)
                GroupSection.JLPT_4 -> sections[4].groups.add(group)
                GroupSection.JLPT_5 -> sections[5].groups.add(group)
            }
        }
        bar.value = sections
    }

    fun observeGroupsOfKanjis() = groupRepository.getAllKanjiGroup()

    fun onWordGroupClick(wordGroup: Group) {
        val direction = WordGroupFragmentDirections.actionWordGroupToWordList().apply {
            wordGroupId = wordGroup.id
        }
        navToWordListEvent.value = ViewModelEvent(direction)
    }
}