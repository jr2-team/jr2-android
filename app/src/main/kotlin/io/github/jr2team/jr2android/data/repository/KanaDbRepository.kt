package io.github.jr2team.jr2android.data.repository

import kotlinx.coroutines.async
import io.github.jr2team.jr2android.App
import io.github.jr2team.jr2android.data.db.AppDatabase
import io.github.jr2team.jr2android.data.db.dao.KanaDao
import io.github.jr2team.jr2android.domain.entity.Kana

class KanaDbRepository(
        db: AppDatabase = App.instance.db
) : BaseDbRepository<Kana>(db.getKanaDao()) {
    private val kanaDao = dao as KanaDao

    fun getAll() =
            async { kanaDao.getAll() }
}