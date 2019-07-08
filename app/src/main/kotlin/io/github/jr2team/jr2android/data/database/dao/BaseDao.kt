package io.github.jr2team.jr2android.data.database.dao

import androidx.room.*

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSingle(o: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMany(o: List<T>): List<Long>

    @Update
    suspend fun update(o: T): Int

    @Delete
    suspend fun delete(o: T): Int
}