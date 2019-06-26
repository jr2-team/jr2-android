package io.github.jr2team.jr2android.data.repository

import kotlinx.coroutines.async
import io.github.jr2team.jr2android.App
import io.github.jr2team.jr2android.data.db.AppDatabase
import io.github.jr2team.jr2android.data.db.dao.GroupDao
import io.github.jr2team.jr2android.domain.entity.Group

class GroupDbRepository(
        db: AppDatabase = App.instance.db
) : BaseDbRepository<Group>(db.getGroupDao()) {
    private val groupDao = dao as GroupDao

    fun getById(groupId: Int) =
            async { groupDao.getById(groupId) }

    fun getAllKanjiGroup() =
            async { groupDao.getAllGroupsOfKanjis() }

    fun getAllWordGroup() =
            async { groupDao.getAllGroupsOfWords() }

    fun getItemsCountInGroup(groupId: Int) =
            async { groupDao.getItemsCountInGroup(groupId) }

}