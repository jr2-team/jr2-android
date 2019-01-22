package ru.jjba.jr2.presentation.viewmodel.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.launch
import ru.jjba.jr2.App
import ru.jjba.jr2.data.repository.GroupDbRepository
import ru.jjba.jr2.data.repository.WordDbRepository
import ru.jjba.jr2.domain.entity.Group
import ru.jjba.jr2.domain.entity.GroupSection
import ru.jjba.jr2.domain.entity.GroupType
import ru.jjba.jr2.domain.entity.Word
import ru.jjba.jr2.presentation.viewmodel.BaseViewModel

class SplashViewModel(
        private val app: App = App.instance,
        private val wordRepository: WordDbRepository = WordDbRepository(),
        private val groupRepository: GroupDbRepository = GroupDbRepository()
) : BaseViewModel() {
    // TODO: Найти способ роутить между активностями через ViewModel
    private val isAllowedToNavToMain = MutableLiveData<Boolean>().apply { value = false }

    fun onSetupDb() = launch {
        val wordsAdapter: JsonAdapter<List<Word>> = Moshi.Builder().build().adapter(
                Types.newParameterizedType(List::class.java, Word::class.java)
        )
        wordsAdapter.fromJson(app.readAsset("word.json"))?.let { words ->
            wordRepository.dropAndInsert(words)
        }
        groupRepository.insertMany(listOf(
            Group(name = "KanjiGroup1", groupType = GroupType.KANJI_GROUP),
            Group(name = "KanjiGroup2", groupType = GroupType.KANJI_GROUP),
            Group(name = "WordGroup1", groupType = GroupType.WORD_GROUP, groupSection = GroupSection.JLPT_5),
            Group(name = "WordGroup2", groupType = GroupType.WORD_GROUP, groupSection = GroupSection.CUSTOM)
        ))

        isAllowedToNavToMain.postValue(true)
    }

    fun observeIsAllowedToNavToMain(): LiveData<Boolean> = isAllowedToNavToMain
}