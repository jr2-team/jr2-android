package ru.jjba.jr2.data.repository

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import ru.jjba.jr2.data.db.dao.BaseDao

open class BaseDbRepository<ET>(
        internal val dao: BaseDao<ET>
) {
    internal val scheduler: Scheduler = Schedulers.io()

    fun insert(value: ET): Completable =
            Completable
                    .fromCallable { dao.insert(value) }
                    .subscribeOn(scheduler)

    fun insert(vararg values: ET): Completable =
            Completable
                    .fromCallable { dao.insert(*values) }
                    .subscribeOn(scheduler)
}