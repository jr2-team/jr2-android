package io.github.jr2team.jr2android.domain.usecase

import com.squareup.moshi.Types
import io.github.jr2team.jr2android.App
import io.github.jr2team.jr2android.data.repository.GroupDbRepository
import io.github.jr2team.jr2android.data.repository.SectionDbRepository
import io.github.jr2team.jr2android.data.repository.WordDbRepository
import io.github.jr2team.jr2android.domain.entity.Group
import io.github.jr2team.jr2android.domain.entity.GroupType
import io.github.jr2team.jr2android.domain.entity.Section
import io.github.jr2team.jr2android.domain.entity.Word
import io.github.jr2team.jr2android.domain.room.join.GroupOfWordsJoin
import kotlin.random.Random

class TestDataUseCase(
        private val app: App = App.instance,
        private val wordRepository: WordDbRepository = WordDbRepository(),
        private val sectionRepository: SectionDbRepository = SectionDbRepository(),
        private val groupRepository: GroupDbRepository = GroupDbRepository()
) {
    suspend fun setupDbData() {
        val wordsAdapter = app.moshi.adapter<List<Word>>(
                Types.newParameterizedType(List::class.java, Word::class.java)
        )
        wordsAdapter.fromJson(app.readAsset("word.json"))?.run {
            wordRepository.dropAndInsert(this)
        }

        sectionRepository.insertMany(listOf(
                Section(0, "Пользовательские группы"),
                Section(1, "JLPT 5"),
                Section(2, "JLPT 4")
        ))

        val wordGroups = mutableListOf<Group>().apply {
            add(Group(id = 1, name = "Custom group", sectionId = 0, groupType = GroupType.WORD_GROUP))
            add(Group(id = 2, name = "JLPT 5 group", sectionId = 1, groupType = GroupType.WORD_GROUP))
            add(Group(id = 3, name = "JLPT 4 group", sectionId = 2, groupType = GroupType.WORD_GROUP))
            addAll((0..100).map {
                Group(name = "group", sectionId = 2, groupType = GroupType.WORD_GROUP)
            })
        }
        groupRepository.insertMany(wordGroups)
        wordRepository.insertWordIntoGroup(
                (1..1000).map { i ->
                    GroupOfWordsJoin(groupId = Random.nextInt(1, 3), wordId = i)
                }
        )
    }
}