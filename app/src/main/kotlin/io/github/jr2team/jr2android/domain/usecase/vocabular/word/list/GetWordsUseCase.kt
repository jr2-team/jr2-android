package io.github.jr2team.jr2android.domain.usecase.vocabular.word.list

import io.github.jr2team.jr2android.data.repository.GroupDbRepository
import io.github.jr2team.jr2android.data.repository.WordDbRepository
import io.github.jr2team.jr2android.domain.room_entities.entity.Group
import io.github.jr2team.jr2android.domain.room_entities.entity.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetWordsUseCase(
    private val groupId: Int
) {
    private val groupRepository: GroupDbRepository = GroupDbRepository()
    private val wordRepository: WordDbRepository = WordDbRepository()

    suspend fun execute() = withContext(Dispatchers.IO) {
        val group = groupRepository.getById(groupId)
        val words = wordRepository.getWordsByGroupId(groupId)
        return@withContext Result(group, words)
    }

    inner class Result(val group: Group, val words: List<Word>)
}