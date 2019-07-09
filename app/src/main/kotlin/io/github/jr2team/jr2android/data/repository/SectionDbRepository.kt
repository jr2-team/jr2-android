package io.github.jr2team.jr2android.data.repository

import io.github.jr2team.jr2android.App
import io.github.jr2team.jr2android.data.database.AppDatabase
import io.github.jr2team.jr2android.data.database.dao.SectionDao
import io.github.jr2team.jr2android.domain.room_entities.entity.Section

class SectionDbRepository(
    db: AppDatabase = App.instance.db
) : BaseDbRepository<Section>(db.getSectionDao()) {
    private val sectionDao = dao as SectionDao

    suspend fun getSectionsWithGroups() = sectionDao.getSectionsWithGroups()
}