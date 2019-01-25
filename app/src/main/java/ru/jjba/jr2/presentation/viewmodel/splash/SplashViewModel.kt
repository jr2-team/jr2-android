package ru.jjba.jr2.presentation.viewmodel.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.launch
import ru.jjba.jr2.App
import ru.jjba.jr2.data.repository.GroupDbRepository
import ru.jjba.jr2.data.repository.SectionDbRepository
import ru.jjba.jr2.data.repository.WordDbRepository
import ru.jjba.jr2.domain.entity.Group
import ru.jjba.jr2.domain.entity.Section
import ru.jjba.jr2.domain.entity.Word
import ru.jjba.jr2.domain.join.GroupOfWordsJoin
import ru.jjba.jr2.presentation.viewmodel.BaseViewModel

class SplashViewModel(
        private val app: App = App.instance,
        private val wordRepository: WordDbRepository = WordDbRepository(),
        private val sectionRepository: SectionDbRepository = SectionDbRepository(),
        private val groupRepository: GroupDbRepository = GroupDbRepository()
) : BaseViewModel() {
    // TODO: Найти способ роутить между активностями через ViewModel
    private val isAllowedToNavToMain = MutableLiveData<Boolean>().apply { value = true }

    fun observeIsAllowedToNavToMain(): LiveData<Boolean> = isAllowedToNavToMain

    fun onSetupDb() = launch {
        val wordsAdapter: JsonAdapter<List<Word>> = Moshi.Builder().build().adapter(
                Types.newParameterizedType(List::class.java, Word::class.java)
        )
        wordsAdapter.fromJson(app.readAsset("word.json"))?.run {
            wordRepository.dropAndInsert(this).await()
        }

        sectionRepository.insertMany(listOf(
                Section(0, "Пользовательские группы"),
                Section(1, "JLPT 5"),
                Section(2, "JLPT 4")
        )).await()

        val groups = mutableListOf<Group>()
        groups.add(Group(id = 1, name = "Custom group", sectionId = 0))
        groups.add(Group(id = 2, name = "JLPT 5 group", sectionId = 1))
        groups.add(Group(id = 3, name = "JLPT 4 group", sectionId = 2))
        groups.addAll((0..100).map { Group(name = "NEW group", sectionId = 2) })
        groupRepository.insertMany(groups).await()
        wordRepository.insertWordIntoGroup(listOf(
                GroupOfWordsJoin(groupId = 1, wordId = 5),
                GroupOfWordsJoin(groupId = 1, wordId = 6),
                GroupOfWordsJoin(groupId = 1, wordId = 7)
        )).await()
    }.invokeOnCompletion { cause ->
        if (cause == null) {
            isAllowedToNavToMain.postValue(true)
        }
    }
}