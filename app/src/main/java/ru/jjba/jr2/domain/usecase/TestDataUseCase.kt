package ru.jjba.jr2.domain.usecase

import com.squareup.moshi.Types
import kotlinx.coroutines.withContext
import ru.jjba.jr2.App
import ru.jjba.jr2.data.repository.GroupDbRepository
import ru.jjba.jr2.data.repository.SectionDbRepository
import ru.jjba.jr2.data.repository.WordDbRepository
import ru.jjba.jr2.domain.entity.Group
import ru.jjba.jr2.domain.entity.GroupType
import ru.jjba.jr2.domain.entity.Section
import ru.jjba.jr2.domain.entity.Word
import ru.jjba.jr2.domain.room.join.GroupOfWordsJoin
import kotlin.coroutines.coroutineContext
import kotlin.random.Random

class TestDataUseCase(
        private val app: App = App.instance,
        private val wordRepository: WordDbRepository = WordDbRepository(),
        private val sectionRepository: SectionDbRepository = SectionDbRepository(),
        private val groupRepository: GroupDbRepository = GroupDbRepository()
) {
    suspend fun setupDbData() = withContext(coroutineContext) {
        val wordsAdapter = app.moshi.adapter<List<Word>>(
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

        val wordGroups = mutableListOf<Group>().apply {
            add(Group(id = 1, name = "Custom group", sectionId = 0, groupType = GroupType.WORD_GROUP))
            add(Group(id = 2, name = "JLPT 5 group", sectionId = 1, groupType = GroupType.WORD_GROUP))
            add(Group(id = 3, name = "JLPT 4 group", sectionId = 2, groupType = GroupType.WORD_GROUP))
            addAll((0..100).map {
                Group(name = "group", sectionId = 2, groupType = GroupType.WORD_GROUP)
            })
        }
        groupRepository.insertMany(wordGroups).await()
        wordRepository.insertWordIntoGroup(
                (1..1000).map { i ->
                    GroupOfWordsJoin(groupId = Random.nextInt(1, 3), wordId = i)
                }
        ).await()
    }
}