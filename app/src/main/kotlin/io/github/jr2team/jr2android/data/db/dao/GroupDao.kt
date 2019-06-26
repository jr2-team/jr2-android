package io.github.jr2team.jr2android.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import io.github.jr2team.jr2android.domain.entity.Group

@Dao
abstract class GroupDao : BaseDao<Group> {
    @Query("SELECT * FROM `Group` WHERE id = :groupId")
    abstract fun getById(groupId: Int): Group

    @Query("SELECT * FROM `Group` WHERE groupType = 0")
    abstract fun getAllGroupsOfKanjis(): List<Group>

    @Query("SELECT * FROM `Group` WHERE groupType = 1")
    abstract fun getAllGroupsOfWords(): List<Group>

    @Query("SELECT COUNT(id) FROM `GroupOfWordsJoin` WHERE groupId = :groupId")
    abstract fun getItemsCountInGroup(groupId: Int): Int
}