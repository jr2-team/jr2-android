package ru.jjba.jr2.data.repository

import androidx.lifecycle.LiveData
import ru.jjba.jr2.App
import ru.jjba.jr2.data.db.AppDatabase
import ru.jjba.jr2.data.db.dao.KanjiDao
import ru.jjba.jr2.domain.entity.Kanji

class KanjiDbRepository(
        db: AppDatabase = App.instance.db
) : BaseDbRepository<Kanji>(db.getKanjiDao()) {
    fun getAll(): LiveData<List<Kanji>> = (dao as KanjiDao).getAll()

    fun getKanjiComponents(kanjiId: Int) = (dao as KanjiDao).getKanjiComponents(kanjiId)
}