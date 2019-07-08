package io.github.jr2team.jr2android.data.repository

import kotlinx.coroutines.async
import io.github.jr2team.jr2android.App
import io.github.jr2team.jr2android.data.database.AppDatabase
import io.github.jr2team.jr2android.data.database.dao.MojiDao
import io.github.jr2team.jr2android.domain.entity.Moji

class MojiDbRepository(
        db: AppDatabase = App.instance.db
) : BaseDbRepository<Moji>(db.getMojiDao()) {
    private val mojiDao = dao as MojiDao

    suspend fun getAll() = mojiDao.getAll()

    suspend fun getMojiComponents(mojiId: Int) = mojiDao.getKanjiComponents(mojiId)
}