package ru.jjba.jr2.data.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import ru.jjba.jr2.App
import ru.jjba.jr2.data.db.AppDatabase
import ru.jjba.jr2.data.db.dao.InterpDao
import ru.jjba.jr2.domain.entity.Interpretation

class InterpretationDbRepository(
        db: AppDatabase = App.instance.db,
        private val scheduler: Scheduler = Schedulers.io()
) {
    private val interpretationDao: InterpDao = db.getInterpDao()

    fun getByWordId(wordId: Long): Flowable<List<Interpretation>> =
            interpretationDao.getByWordId(wordId)
                    .flatMap {
                        Flowable.fromIterable(it)
                                .toList()
                                .toFlowable()
                    }
                    .subscribeOn(scheduler)

    fun insert(interpretation: Interpretation): Completable =
            Completable.fromCallable { interpretationDao.insert(interpretation) }
                    .subscribeOn(scheduler)

    fun insert(interpretations: List<Interpretation>): Completable =
            Completable.fromCallable { interpretationDao.insert(interpretations) }
                    .subscribeOn(scheduler)
}