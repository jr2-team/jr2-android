package io.github.jr2team.jr2android.data.repository

import io.github.jr2team.jr2android.App
import io.github.jr2team.jr2android.data.database.AppDatabase
import io.github.jr2team.jr2android.data.database.dao.KanjiDao
import io.github.jr2team.jr2android.domain.room_entities.entity.Kanji

class KanjiDbRepository(
    db: AppDatabase = App.instance.db
) : BaseDbRepository<Kanji>(db.getKanjiDao()) {
    private val kanjiDao = dao as KanjiDao

    suspend fun getAll() = kanjiDao.getAll()
}