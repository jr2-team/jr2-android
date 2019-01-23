package ru.jjba.jr2.data.repository

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import ru.jjba.jr2.App
import ru.jjba.jr2.data.db.AppDatabase
import ru.jjba.jr2.data.db.dao.WordDao
import ru.jjba.jr2.domain.entity.Word
import ru.jjba.jr2.domain.join.GroupOfWordJoin

class WordDbRepository(
        db: AppDatabase = App.instance.db
) : BaseDbRepository<Word>(db.getWordDao()) {
    private val wordDao = dao as WordDao

    fun getById(wordId: Int) =
            wordDao.getById(wordId)

    fun getAll() =
            wordDao.getAll()

    fun getWordsByGroupId(groupId: Int) =
            wordDao.getWordsByGroupId(groupId)

    suspend fun insertWordIntoGroup(groupOfWordJoin: List<GroupOfWordJoin>) = withContext(IO) {
        wordDao.insertWordIntoGroup(groupOfWordJoin)
    }

    suspend fun dropAndInsert(words: List<Word>) = withContext(IO) {
        wordDao.dropAndInsert(words)
    }
}