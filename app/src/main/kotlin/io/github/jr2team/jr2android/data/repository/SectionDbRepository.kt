package io.github.jr2team.jr2android.data.repository

import io.github.jr2team.jr2android.App
import io.github.jr2team.jr2android.data.db.AppDatabase
import io.github.jr2team.jr2android.data.db.dao.SectionDao
import io.github.jr2team.jr2android.domain.entity.Section

class SectionDbRepository(
        db: AppDatabase = App.instance.db
) : BaseDbRepository<Section>(db.getSectionDao()) {
    private val sectionDao = dao as SectionDao

    suspend fun getSectionsWithGroups() = sectionDao.getSectionsWithGroups()
}