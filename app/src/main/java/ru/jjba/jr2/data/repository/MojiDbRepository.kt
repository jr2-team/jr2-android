package ru.jjba.jr2.data.repository

import kotlinx.coroutines.async
import ru.jjba.jr2.App
import ru.jjba.jr2.data.db.AppDatabase
import ru.jjba.jr2.data.db.dao.MojiDao
import ru.jjba.jr2.domain.entity.Moji

class MojiDbRepository(
        db: AppDatabase = App.instance.db
) : BaseDbRepository<Moji>(db.getMojiDao()) {
    private val mojiDao = dao as MojiDao

    fun getAll() =
            async { mojiDao.getAll() }

    fun getMojiComponents(mojiId: Int) =
            async { mojiDao.getKanjiComponents(mojiId) }
}