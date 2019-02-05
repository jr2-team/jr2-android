package ru.jjba.jr2.data.repository

import kotlinx.coroutines.async
import ru.jjba.jr2.App
import ru.jjba.jr2.data.db.AppDatabase
import ru.jjba.jr2.data.db.dao.WordDao
import ru.jjba.jr2.domain.entity.Word
import ru.jjba.jr2.domain.room.join.GroupOfWordsJoin

class WordDbRepository(
        db: AppDatabase = App.instance.db
) : BaseDbRepository<Word>(db.getWordDao()) {
    private val wordDao = dao as WordDao

    fun getById(wordId: Int) =
            async { wordDao.getById(wordId) }

    fun getAll() =
            async { wordDao.getAll() }

    fun getWordsByGroupId(groupId: Int) =
            async { wordDao.getWordsByGroupId(groupId) }

    fun insertWordIntoGroup(groupOfWordJoin: List<GroupOfWordsJoin>) =
            async { wordDao.insertWordIntoGroup(groupOfWordJoin) }

    fun dropAndInsert(words: List<Word>) =
            async { wordDao.dropAndInsert(words) }
}