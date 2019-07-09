package io.github.jr2team.jr2android.data.repository

import io.github.jr2team.jr2android.App
import io.github.jr2team.jr2android.data.database.AppDatabase
import io.github.jr2team.jr2android.data.database.dao.GroupDao
import io.github.jr2team.jr2android.domain.room_entities.entity.Group

class GroupDbRepository(
    db: AppDatabase = App.instance.db
) : BaseDbRepository<Group>(db.getGroupDao()) {
    private val groupDao = dao as GroupDao

    suspend fun getById(groupId: Int) = groupDao.getById(groupId)

    suspend fun getAllKanjiGroup() = groupDao.getAllGroupsOfKanjis()

    suspend fun getAllWordGroup() = groupDao.getAllGroupsOfWords()

    suspend fun getItemsInGroup(groupId: Int) = groupDao.getItemsInGroup(groupId)
}