package ru.jjba.jr2.data.repository.kanji

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import ru.jjba.jr2.App
import ru.jjba.jr2.data.db.AppDatabase
import ru.jjba.jr2.data.db.dao.KanjiDao
import ru.jjba.jr2.domain.entity.Kanji
import ru.jjba.jr2.domain.entity.KanjiPart

class KanjiDbRepository(
        db: AppDatabase = App.instance.db,
        private val scheduler: Scheduler = Schedulers.io()
) {
    private val kanjiDao: KanjiDao = db.getKanjiDao()

    fun getAll(): Flowable<List<Kanji>> =
            kanjiDao.getAll()
                    .subscribeOn(scheduler)

    fun getKanjiPartsByKanjiId(kanjiId: String): Flowable<List<Kanji>> =
            kanjiDao.getKanjiPartsByKanjiId(kanjiId)
                    .flatMap {
                        Flowable.fromIterable(it)
                                .toList()
                                .toFlowable()
                    }
                    .subscribeOn(scheduler)

    fun insert(kanji: Kanji): Completable =
            Completable.fromCallable { kanjiDao.insert(kanji) }
                    .subscribeOn(scheduler)

    fun insert(kanjis: List<Kanji>): Completable =
            Completable.fromCallable { kanjiDao.insert(kanjis) }
                    .subscribeOn(scheduler)

    fun insertKanjiPart(kanjiPart: KanjiPart): Completable =
            Completable.fromCallable { kanjiDao.insertKanjiPart(kanjiPart) }
                    .subscribeOn(scheduler)

    fun insertKanjiPart(kanjiParts: List<KanjiPart>): Completable =
            Completable.fromCallable { kanjiDao.insertKanjiPart(kanjiParts) }
                    .subscribeOn(scheduler)
}