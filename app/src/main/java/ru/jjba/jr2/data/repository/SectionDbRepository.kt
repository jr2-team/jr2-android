package ru.jjba.jr2.data.repository

import ru.jjba.jr2.App
import ru.jjba.jr2.data.db.AppDatabase
import ru.jjba.jr2.data.db.dao.SectionDao
import ru.jjba.jr2.domain.entity.Section

class SectionDbRepository(
        db: AppDatabase = App.instance.db
) : BaseDbRepository<Section>(db.getSectionDao()) {
    val sectionDao = dao as SectionDao

    fun getSectionsWithGroups() =
            sectionDao.getSectionsWithGroups()
}