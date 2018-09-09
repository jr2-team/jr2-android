package ru.jjba.jr2.data.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.intellij.lang.annotations.Flow
import ru.jjba.jr2.App
import ru.jjba.jr2.data.db.AppDatabase
import ru.jjba.jr2.data.db.dao.KanaDao
import ru.jjba.jr2.domain.entity.Kana

class KanaDbRepository(
        db: AppDatabase = App.instance.db,
        private val scheduler: Scheduler = Schedulers.io()
) {
    private val kanaDao: KanaDao = db.getKanaDao()

    fun getAll(): Flowable<List<Kana>> = kanaDao.getAll().subscribeOn(scheduler)

    fun getOnlyKana(): Flowable<List<Kana>> = kanaDao.getOnlyKana().subscribeOn(scheduler)

    fun getOnlyAdditionalSound(): Flowable<List<Kana>> =
            kanaDao.getOnlyAdditionalSound().subscribeOn(scheduler)

    fun insert(piecesOfKana: List<Kana>): Completable =
            Completable.fromCallable { kanaDao.insert(piecesOfKana) }
                    .subscribeOn(scheduler)
}