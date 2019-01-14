package ru.jjba.jr2.data.repository

import androidx.lifecycle.LiveData
import ru.jjba.jr2.App
import ru.jjba.jr2.data.db.AppDatabase
import ru.jjba.jr2.data.db.dao.KanaDao
import ru.jjba.jr2.domain.entity.Kana

class KanaDbRepository(
        db: AppDatabase = App.instance.db
) : BaseDbRepository<Kana>(db.getKanaDao()) {
    fun getAll(): LiveData<List<Kana>> = (dao as KanaDao).getAll()
}