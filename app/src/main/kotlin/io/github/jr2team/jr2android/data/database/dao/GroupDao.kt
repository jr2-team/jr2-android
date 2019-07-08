package io.github.jr2team.jr2android.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import io.github.jr2team.jr2android.domain.entity.Group

@Dao
abstract class GroupDao : BaseDao<Group> {
    @Query("SELECT * FROM `Group` WHERE id = :groupId")
    abstract suspend fun getById(groupId: Int): Group

    @Query("SELECT * FROM `Group` WHERE groupType = 0")
    abstract suspend fun getAllGroupsOfKanjis(): List<Group>

    @Query("SELECT * FROM `Group` WHERE groupType = 1")
    abstract suspend fun getAllGroupsOfWords(): List<Group>

    @Query("SELECT COUNT(id) FROM `GroupOfWordsJoin` WHERE groupId = :groupId")
    abstract suspend  fun getItemsCountInGroup(groupId: Int): Int
}