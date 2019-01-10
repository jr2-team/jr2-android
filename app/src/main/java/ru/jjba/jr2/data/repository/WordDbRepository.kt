package ru.jjba.jr2.data.repository

import io.reactivex.Flowable
import io.reactivex.Single
import ru.jjba.jr2.App
import ru.jjba.jr2.data.db.AppDatabase
import ru.jjba.jr2.data.db.dao.WordDao
import ru.jjba.jr2.domain.entity.Word

//TODO : Сделать дженерик BaseDbRepository
class WordDbRepository(
        db: AppDatabase = App.instance.db
) : BaseDbRepository<Word>(db.getWordDao()) {
    private val wordDao = dao as WordDao

    fun getById(wordId: Long): Single<Word> = wordDao.getById(wordId).subscribeOn(scheduler)

    fun getAll(): Flowable<List<Word>> = wordDao.getAll().subscribeOn(scheduler)
}