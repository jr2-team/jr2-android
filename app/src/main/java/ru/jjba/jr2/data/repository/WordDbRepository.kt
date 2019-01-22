package ru.jjba.jr2.data.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import ru.jjba.jr2.App
import ru.jjba.jr2.data.db.AppDatabase
import ru.jjba.jr2.data.db.dao.WordDao
import ru.jjba.jr2.domain.entity.Word

class WordDbRepository(
        db: AppDatabase = App.instance.db
) : BaseDbRepository<Word>(db.getWordDao()) {
    private val wordDao = dao as WordDao

    fun getById(wordId: Int) = wordDao.getById(wordId)

    fun getAll() = wordDao.getAll()

    fun getByQuery(query: String) = wordDao.getByQuery(query)

    suspend fun dropAndInsert(words: List<Word>) = withContext(IO) {
        wordDao.dropAndInsert(words)
    }
}