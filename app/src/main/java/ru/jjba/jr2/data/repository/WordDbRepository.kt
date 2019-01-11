package ru.jjba.jr2.data.repository

import androidx.lifecycle.LiveData
import io.reactivex.Completable
import io.reactivex.Single
import ru.jjba.jr2.App
import ru.jjba.jr2.data.db.AppDatabase
import ru.jjba.jr2.data.db.dao.WordDao
import ru.jjba.jr2.domain.entity.Word

class WordDbRepository(
        db: AppDatabase = App.instance.db
) : BaseDbRepository<Word>(db.getWordDao()) {
    fun getById(wordId: Int): Single<Word> =
            (dao as WordDao)
                    .getById(wordId)
                    .subscribeOn(scheduler)

    fun getAll(): LiveData<List<Word>> = (dao as WordDao).getAll()

    fun dropAndInsert(words: List<Word>): Completable =
            Completable
                    .fromCallable { (dao as WordDao).dropAndInsert(words) }
                    .subscribeOn(scheduler)
}