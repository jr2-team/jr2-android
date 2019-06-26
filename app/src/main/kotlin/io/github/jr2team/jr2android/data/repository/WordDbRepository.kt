package io.github.jr2team.jr2android.data.repository

import kotlinx.coroutines.async
import io.github.jr2team.jr2android.App
import io.github.jr2team.jr2android.data.db.AppDatabase
import io.github.jr2team.jr2android.data.db.dao.WordDao
import io.github.jr2team.jr2android.domain.entity.Word
import io.github.jr2team.jr2android.domain.room.join.GroupOfWordsJoin

class WordDbRepository(
        db: AppDatabase = App.instance.db
) : BaseDbRepository<Word>(db.getWordDao()) {
    private val wordDao = dao as WordDao

    fun getById(wordId: Int) =
            async { wordDao.getById(wordId) }

    fun getAll() =
            async { wordDao.getAll() }

    suspend fun getWordsByGroupId(groupId: Int) = wordDao.getWordsByGroupId(groupId)

    suspend fun insertWordIntoGroup(groupOfWordJoin: List<GroupOfWordsJoin>) =
        wordDao.insertWordIntoGroup(groupOfWordJoin)

    suspend fun dropAndInsert(words: List<Word>) = wordDao.dropAndInsert(words)
}