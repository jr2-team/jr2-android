package ru.jjba.jr2.data.repository.word

import ru.jjba.jr2.App
import ru.jjba.jr2.data.db.AppDatabase
import ru.jjba.jr2.data.db.dao.WordDao
import ru.jjba.jr2.domain.entity.Word

class WordDbRepository(
        db: AppDatabase = App.instance.room
) {
    private val wordDao: WordDao = db.getWordDao()

    fun insert(word: Word) = wordDao.insert(word)
}