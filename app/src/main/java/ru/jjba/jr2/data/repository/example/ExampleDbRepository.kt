package ru.jjba.jr2.data.repository.example

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import ru.jjba.jr2.App
import ru.jjba.jr2.data.db.AppDatabase
import ru.jjba.jr2.data.db.dao.ExampleDao

class ExampleDbRepository(
        db: AppDatabase = App.instance.db,
        private val scheduler: Scheduler = Schedulers.io()
) {
    private val exampleDao: ExampleDao = db.getExample()
}