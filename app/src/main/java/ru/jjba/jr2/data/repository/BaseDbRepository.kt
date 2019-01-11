package ru.jjba.jr2.data.repository

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import ru.jjba.jr2.data.db.dao.BaseDao

abstract class BaseDbRepository<ET>(
        internal val dao: BaseDao<ET>
) {
    internal val scheduler: Scheduler = Schedulers.io()

    fun insertSingle(value: ET): Completable =
            Completable
                    .fromCallable { dao.insertSingle(value) }
                    .subscribeOn(scheduler)

    fun insertMany(values: List<ET>): Completable =
            Completable
                    .fromCallable { dao.insertMany(values) }
                    .subscribeOn(scheduler)
}