package ru.jjba.jr2.data.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import ru.jjba.jr2.App
import ru.jjba.jr2.data.db.AppDatabase
import ru.jjba.jr2.domain.entity.Example

class ExampleDbRepository(
        db: AppDatabase = App.instance.db,
        private val scheduler: Scheduler = Schedulers.io()
) {
    private val exampleDao = db.getExampleDao()

    fun getAll(): Flowable<List<Example>> =
            exampleDao.getAll()
                    .subscribeOn(scheduler)

    fun getByInterpId(interpId: String): Flowable<List<Example>> =
            exampleDao.getByInterpId(interpId)
                    .flatMap {
                        Flowable.fromIterable(it)
                                .toList()
                                .toFlowable()
                    }
                    .subscribeOn(scheduler)

    fun insert(example: Example): Completable =
            Completable.fromCallable { exampleDao.insert(example) }
                    .subscribeOn(scheduler)

    fun insert(examples: List<Example>): Completable =
            Completable.fromCallable { exampleDao.insert(examples) }
                    .subscribeOn(scheduler)
}