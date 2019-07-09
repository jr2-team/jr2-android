package io.github.jr2team.jr2android.data.repository

import io.github.jr2team.jr2android.App
import io.github.jr2team.jr2android.data.database.AppDatabase
import io.github.jr2team.jr2android.data.database.dao.KanaDao
import io.github.jr2team.jr2android.domain.room_entities.entity.Kana

class KanaDbRepository(
    db: AppDatabase = App.instance.db
) : BaseDbRepository<Kana>(db.getKanaDao()) {
    private val kanaDao = dao as KanaDao

    suspend fun getAll() = kanaDao.getAll()
}