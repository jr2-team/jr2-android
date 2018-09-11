package ru.jjba.jr2.data.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.jjba.jr2.App
import ru.jjba.jr2.data.db.AppDatabase
import ru.jjba.jr2.data.db.dao.InterpDao
import ru.jjba.jr2.data.db.dao.WordDao
import ru.jjba.jr2.domain.entity.Word

class WordDbRepository(
        db: AppDatabase = App.instance.db,
        private val scheduler: Scheduler = Schedulers.io()
) {
    private val wordDao: WordDao = db.getWordDao()
    private val interpDao: InterpDao = db.getInterpDao()

    fun getById(wordId: Long): Single<Word> =
            wordDao.getById(wordId)
                    .subscribeOn(scheduler)

    fun getAll(): Flowable<List<Word>> =
            wordDao.getAll()
                    .subscribeOn(scheduler)

    fun insert(word: Word): Completable =
            Completable.fromCallable { wordDao.insert(word) }
                    .subscribeOn(scheduler)

    fun insert(words: List<Word>): Completable =
            Completable.fromAction {
                words.forEach {word ->
                    val wordId = wordDao.insert(word)
                    word.interps.forEach {
                        it.word = wordId
                    }
                    interpDao.insert(word.interps)
                }
            }.subscribeOn(scheduler)
}