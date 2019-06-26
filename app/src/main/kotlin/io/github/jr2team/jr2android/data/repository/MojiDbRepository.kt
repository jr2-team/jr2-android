package io.github.jr2team.jr2android.data.repository

import kotlinx.coroutines.async
import io.github.jr2team.jr2android.App
import io.github.jr2team.jr2android.data.db.AppDatabase
import io.github.jr2team.jr2android.data.db.dao.MojiDao
import io.github.jr2team.jr2android.domain.entity.Moji

class MojiDbRepository(
        db: AppDatabase = App.instance.db
) : BaseDbRepository<Moji>(db.getMojiDao()) {
    private val mojiDao = dao as MojiDao

    fun getAll() =
            async { mojiDao.getAll() }

    fun getMojiComponents(mojiId: Int) =
            async { mojiDao.getKanjiComponents(mojiId) }
}