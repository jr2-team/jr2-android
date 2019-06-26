package io.github.jr2team.jr2android.data.db.dao

import androidx.room.*

interface BaseDao<ET> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSingle(o: ET): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMany(o: List<ET>): List<Long>

    @Update
    suspend fun update(o: ET): Int

    @Delete
    suspend fun delete(o: ET): Int
}