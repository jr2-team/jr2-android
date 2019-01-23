package ru.jjba.jr2.data.repository

import kotlinx.coroutines.async
import ru.jjba.jr2.App
import ru.jjba.jr2.data.db.AppDatabase
import ru.jjba.jr2.data.db.dao.GroupDao
import ru.jjba.jr2.domain.entity.Group

class GroupDbRepository(
        db: AppDatabase = App.instance.db
) : BaseDbRepository<Group>(db.getGroupDao()) {
    private val groupDao = dao as GroupDao

    fun getById(groupId: Int) = async {
        groupDao.getById(groupId)
    }

    fun getAllKanjiGroup() = groupDao.getAllKanjiGroups()

    fun getAllWordGroup() = groupDao.getAllWordGroups()
}