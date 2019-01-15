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
    fun getById(wordId: Int): LiveData<Word> = (dao as WordDao).getById(wordId)

    fun getAll(): LiveData<List<Word>> = (dao as WordDao).getAll()

    suspend fun dropAndInsert(words: List<Word>) = withContext(IO) {
        (dao as WordDao).dropAndInsert(words)
    }
}